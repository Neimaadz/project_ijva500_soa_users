package com.cedalanavi.project_ijva500_soa_users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.cedalanavi.projet_IJVA500_SOA_utils.JwtAuthEntryPoint;
import com.cedalanavi.projet_IJVA500_SOA_utils.JwtRequestFilter;
import com.cedalanavi.projet_IJVA500_SOA_utils.RestTemplateConfig;

@SpringBootApplication
@Import({ RestTemplateConfig.class, JwtAuthEntryPoint.class, JwtRequestFilter.class })
public class ProjectIjva500SoaUsersApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectIjva500SoaUsersApplication.class, args);
	}

}
