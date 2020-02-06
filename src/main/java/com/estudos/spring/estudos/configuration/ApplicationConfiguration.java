package com.estudos.spring.estudos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
	
	@Bean
	public ServiceConfiguration serviceConfig() {
		ServiceConfiguration serviceConfiguration = new ServiceConfiguration();
		
		serviceConfiguration.setConfiguracaoInicial("Configuracao Inicial");
		serviceConfiguration.setConfiguracaoFinal("Configuracao Final");
		
		return serviceConfiguration;
	}

}
