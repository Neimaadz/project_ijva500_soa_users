package com.cedalanavi.projet_IJVA500_SOA_users.Security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	@Bean(name = "myRestTemplate")
	public RestTemplate collectCentRestTemplate(RestTemplateBuilder builder, HttpServletRequest httpServletRequest) {
	    return builder.rootUri("/**")
	            .additionalInterceptors((ClientHttpRequestInterceptor) (request, body, execution) -> {
	                request.getHeaders().add("Authorization", httpServletRequest.getHeader("Authorization"));
	                return execution.execute(request, body);
	            }).build();
	}
}
