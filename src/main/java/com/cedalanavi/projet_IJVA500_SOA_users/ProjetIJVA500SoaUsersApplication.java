package com.cedalanavi.projet_IJVA500_SOA_users;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProjetIJVA500SoaUsersApplication {

	@Bean(name = "myRestTemplate")
	public RestTemplate collectCentRestTemplate(RestTemplateBuilder builder, HttpServletRequest httpServletRequest) {
	    return builder.rootUri("/**")
	            .additionalInterceptors((ClientHttpRequestInterceptor) (request, body, execution) -> {
	                request.getHeaders().add("Authorization", httpServletRequest.getHeader("Authorization"));
	                return execution.execute(request, body);
	            }).build();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetIJVA500SoaUsersApplication.class, args);
	}

}
