package com.estudos.spring.estudos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.estudos.spring.estudos.requestmodel.PessoaInput;
import com.estudos.spring.estudos.responsemodel.ResponseSimples;

@RequestMapping("/parametros-no-payload")
@RestController
public class RequestMappingComParametrosNoPayload {
	
	@Autowired
	ResponseSimples responseSimples;
	
	//**** RECURSO COM PARÂMETROS INSERIDOS NO PAYLOAD ****
	//Também é possível receber informações no payload da requisição, para isso é necessário ter uma classe que sirva de modelo para receber as informações.
	//Com essa estrutura montada, é necessário usar a anotação @RequestBody, informando o tipo do modelo criado.
	@GetMapping("/json-recebido-no-payload")
	public String getComParametrosNoPayload(@RequestBody PessoaInput input) {  

		return "Obtendo o paraetro recebido. Nome = " + input.getNome() + ", idade = " + input.getIdade();
	}
	
	
	//**** RECURSO COM PARÂMETROS INSERIDOS NO PAYLOAD E RETORNANDO UM RESPONSEENTITY****
	//Conforme o exemplo acima, este recurso recebe informações no payload, mas retorna um ResponseEntity que possibilita manipular de forma precisa
	//o protocolo HTTP.
	@GetMapping("/json-recebido-no-payload-usando-ResponseEntity")
	public ResponseEntity<ResponseSimples> getComParametrosNoPayloadeResponseEntity(@RequestBody PessoaInput input){
		
	    responseSimples.setCodido(1);
	    responseSimples.setDescricao("Nome: " + input.getNome() + " - Idade: " + input.getIdade() );
		
		return ResponseEntity.ok().body(responseSimples);
		
	}
}
