package com.TaskCyberMAK.api.soap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

@Configuration
public class SoapClientConfig {

    @Value("${hlr.soap.username}")
    private String username;

    @Value("${hlr.soap.password}")
    private String password;

    @Value("${hlr.soap.timeout}")
    private int timeout;

    @Bean
    public Wss4jSecurityInterceptor securityInterceptor() {
        Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();
        interceptor.setSecurementActions("UsernameToken");
        interceptor.setSecurementUsername(username);
        interceptor.setSecurementPassword(password);
        interceptor.setSecurementPasswordType("PasswordText");
        return interceptor;
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

        marshaller.setPackagesToScan("com.TaskCyberMAK.api.soap.generated");
        return marshaller;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(
            Wss4jSecurityInterceptor securityInterceptor,
            Jaxb2Marshaller marshaller
    ) {
        WebServiceTemplate template = new WebServiceTemplate();
        template.setMarshaller(marshaller);
        template.setUnmarshaller(marshaller);

        HttpComponentsMessageSender messageSender = new HttpComponentsMessageSender();
        messageSender.setConnectionTimeout(timeout);
        messageSender.setReadTimeout(timeout);
        template.setMessageSender(messageSender);

        template.setInterceptors(new org.springframework.ws.client.support.interceptor.ClientInterceptor[]{securityInterceptor});
        return template;
    }
}
