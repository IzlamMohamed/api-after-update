package com.TaskCyberMAK.api.service;

import com.TaskCyberMAK.api.dto.NormalViewResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.client.SoapFaultClientException;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
@Log4j2
@Service
@RequiredArgsConstructor
public class HlrSoapClient {

    private final WebServiceTemplate webServiceTemplate;

    @Value("${hlr.soap.endpoint}")
    private String soapEndpoint;

    @Value("${hlr.soap.username}")
    private String soapUsername;

    @Value("${hlr.soap.password}")
    private String soapPassword;

    public NormalViewResponse callNormalView(String msisdn) {
        String requestPayload = buildSoapRequest(msisdn);

        try {
            String responseXml = webServiceTemplate.sendAndReceive(
                    soapEndpoint,
                    message -> {
                        try {
                            // Write request body
                            StreamSource source = new StreamSource(new StringReader(requestPayload));
                            TransformerFactory.newInstance()
                                    .newTransformer()
                                    .transform(source, message.getPayloadResult());

                            // Add WS-Security header
                            if (message instanceof SoapMessage) {
                                addWsSecurityHeader((SoapMessage) message);
                            }
                        } catch (Exception e) {
                            throw new RuntimeException("Error writing SOAP request", e);
                        }
                    },
                    message -> {
                        try {
                            StringWriter writer = new StringWriter();
                            TransformerFactory.newInstance()
                                    .newTransformer()
                                    .transform(message.getPayloadSource(), new StreamResult(writer));
                            return writer.toString();
                        } catch (Exception e) {
                            throw new RuntimeException("Error reading SOAP response", e);
                        }
                    }
            );

            return parseSoapResponse(responseXml);

        } catch (SoapFaultClientException e) {
            throw new RuntimeException("SOAP Fault: " + e.getFaultStringOrReason());
        } catch (Exception e) {
            throw new RuntimeException("SOAP Client Error: " + e.getMessage(), e);
        }
    }

    private String buildSoapRequest(String msisdn) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hlr=\"http://example.com/hlr\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <hlr:NormalViewRequest>\n" +
                "         <hlr:msisdn>" + msisdn + "</hlr:msisdn>\n" +
                "      </hlr:NormalViewRequest>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }

    private void addWsSecurityHeader(SoapMessage message) {
        try {
            String securityHeaderXml =
                    "<wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" " +
                            "xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n" +
                            "   <wsse:UsernameToken>\n" +
                            "      <wsse:Username>" + soapUsername + "</wsse:Username>\n" +
                            "      <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">" + soapPassword + "</wsse:Password>\n" +
                            "   </wsse:UsernameToken>\n" +
                            "</wsse:Security>";

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
            ByteArrayInputStream headerStream = new ByteArrayInputStream(securityHeaderXml.getBytes(StandardCharsets.UTF_8));
            StreamSource headerSource = new StreamSource(headerStream);

            transformer.transform(headerSource, message.getSoapHeader().getResult());

        } catch (Exception e) {
            throw new RuntimeException("Error adding WS-Security header", e);
        }
    }

    private NormalViewResponse parseSoapResponse(String xml) {
    Map xmlResponse = xmlToMap(xml);
        NormalViewResponse response = new NormalViewResponse();
        response.setMsisdn(xmlResponse.getOrDefault("MNP","").toString());
        response.setMnp(xmlResponse.getOrDefault("registeration","").toString());
        response.setRegistration(xmlResponse.getOrDefault("roaming","").toString());
        response.setRoaming(xmlResponse.getOrDefault("international","").toString());
        response.setInternationalAccess(xmlResponse.getOrDefault("subscriberProfile","").toString());
        response.setSubscriberProfile(xmlResponse.getOrDefault("MSISDN","").toString());
        response.setImsi(xmlResponse.getOrDefault("IMSI","").toString());
        response.setMsisdn(xmlResponse.getOrDefault("operatorCallWaitingActivation","").toString());
        response.setMnp(xmlResponse.getOrDefault("userCallWaitingActivation","").toString());
        response.setRegistration(xmlResponse.getOrDefault("hotLine","").toString());
        response.setRoaming(xmlResponse.getOrDefault("international","").toString());
        response.setInternationalAccess(xmlResponse.getOrDefault("subscriberProfile","").toString());
        response.setSubscriberProfile(xmlResponse.getOrDefault("MSISDN","").toString());
        response.setImsi(xmlResponse.getOrDefault("IMSI","").toString());
        return response;
    }
    public static Map<String, Object> xmlToMap(String xml){
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(xml, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("Error in xmlToMap: {}", e.getMessage());
            return new HashMap<>();
        }

    }
}
