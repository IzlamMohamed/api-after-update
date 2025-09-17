package com.TaskCyberMAK.api;

import com.TaskCyberMAK.api.service.HlrSoapClient;
import com.TaskCyberMAK.api.dto.NormalViewRequest;
import com.TaskCyberMAK.api.dto.NormalViewResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ws.client.WebServiceTransportException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class ApiApplicationTests {

	@Autowired
	private HlrSoapClient hlrSoapClient;

	@Test
	void testTimeoutBehavior() {
		String badEndpoint = "http://10.255.255.1";  // non-responsive address
		// You can override soapEndpoint via test property or use constructor (if possible)
		// But here, assume it's configured to use bad endpoint

		assertThatThrownBy(() -> hlrSoapClient.callNormalView("123456789"))
				.isInstanceOf(WebServiceTransportException.class);
	}
}
