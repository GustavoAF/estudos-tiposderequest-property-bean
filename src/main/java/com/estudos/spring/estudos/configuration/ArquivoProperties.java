package com.estudos.spring.estudos.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
@ConfigurationProperties("estudos.properties")
public class ArquivoProperties {
	
	String mensagemArquivo;

}
