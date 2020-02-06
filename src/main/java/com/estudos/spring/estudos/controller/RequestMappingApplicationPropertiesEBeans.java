package com.estudos.spring.estudos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.estudos.spring.estudos.configuration.ArquivoProperties;
import com.estudos.spring.estudos.configuration.ServiceConfiguration;

@RestController
@RequestMapping("/RequestMappingApplicationPropertiesEBeans")
public class RequestMappingApplicationPropertiesEBeans {
	
	@Autowired
	ArquivoProperties arquivoProperties;
	
	@Autowired
	ServiceConfiguration serviceConfig ;
	
	@Value("${estudos.properties.usando-values}")
	private String testePropertiesComValues;

	
	//**** OBTENDO INFORMAÇÕES DO ARQUIVO PROPERTIES ****
	//Neste recurso usamos simplesmente o retorno da mensagem definida no arquivo application.properties, a ideia é mostrar como é fácil obter
	//parametros de um unico arquivo. Para isso é necessario implementar uma classe com as mesmas propriedades definidas no arquivo, será necessario 
	//anotar a classe com @ConfigurationProperties informando como parâmetro o prefixo da propriedade, e o restante da propriedade do arquivo deve ser 
	//a mesma da classe.
	@PostMapping("/application-properties")
	public ResponseEntity<String> usandoApllicationProperties(){
		
		return ResponseEntity.status(HttpStatus.OK).body(arquivoProperties.getMensagemArquivo()); 
	}
	
	//**** OBTENDO INFORMAÇÕES DO ARQUIVO PROPERTIES SEM CRIAR UMA CLASSE USANDO ANOTACAO @VALUE ****
	//Neste recurso usamos uma outra forma de acessar alguma propriedade do arquivo application.properties para isso, basta declarar uma váriavel e acima
	//anota-la com a anotação @Value inserindo o parâmetro string iniciando $ e dentro de {} indicar o nome completo da propriedade.
	@PostMapping("/application-properties-anotacao-values")
	public ResponseEntity<String> usandoApllicationPropertiesComAnotacaoValues(){
		
		return ResponseEntity.status(HttpStatus.OK).body(testePropertiesComValues); 
	}
	
	//**** IMPLEMENTANDO BEANS ****
	//No conceito de injeção de dependecias fica a cargo do container controlar a injeção das classes, como se sabe para isso basta usar a anotação @Componente
	//para criar e @Autowired para injetar. Mas nesses casos se não tivermos um construtor inserindo valores, o objeto virá com valores default.
	//Para resolver essa questão existe a anotação @Bean, que é uma anotação geralmente usada em métodos, essa anotação faz com que determinado metodo seja executada
	//antes da injeção, ou seja a classe que depende recebera o objeto com os valores necessários.
	//Para usar essa técnica precisamos criar a classe que será retornada, uma segunda que receberá a anotação @Configuration e dentro dessa um método que 
	//recebera a anotação @Bean efetuando o inserção dos valores que devem ser retornados. Dessa maneira quando injetarmos o objeto, o mesmo virá preenchido
	//com as informações necessárias.
	@PostMapping("/classe-configuracao")
	public ResponseEntity<String> usandoClasseConfiguracao(){
		
		return ResponseEntity.status(HttpStatus.OK).body("Configuracao Inicial: " + serviceConfig.getConfiguracaoInicial() 
		              + " Configuracao Final: " + serviceConfig.getConfiguracaoFinal());
	}

}
