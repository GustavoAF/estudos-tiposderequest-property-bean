package com.estudos.spring.estudos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.spring.estudos.configuration.ArquivoProperties;
import com.estudos.spring.estudos.responsemodel.ResponseSimples;


@RequestMapping("/metodo-sem-parametro")
@RestController
public class RequestMappingSimplesSemParametros {
	
	@Autowired
	ArquivoProperties arquivoProperties;
	
	//Parâmetro não é um item obrigatório para um recurso, é possível desenvolver sem a necessidade de um alguma informação no request.
	
	@Autowired
	ResponseSimples responseSimples;
	

	//**** RETORNO DE TIPO PRIMITIVO ****
	//Recurso não necessáriamente precisa receber um parâmetro, tanto por uri quanto no payload. Nesse exemplo temos um método extremamente 
	//simples que não recebe nenhum parâmetro de entrada e retorna um tipo String. O controlador esta anotado com @RestController o que possibilita 
	//o retorno identico ao que foi enviado. A diferença nesses casos é que a propriedade Content-Type do Header no response terá o valor text/plain, 
	//porque o tipo de retorno não foi informado, por isso o proprio spring sugere o tipo.
    @GetMapping ("/retornando-primitivo")
	public String getRetornoTipoPrimitivo()  {
    	 
		return "Retornando um tipo primitivo.";
	}
    
    //**** RETORNO DE TIPO POR REFERENCIA (JSON) ****
    //No exemplo abaixo, o request do verbo get continua sem receber parâmetros de entrada, mas diferente do caso acima, seu 
    //retorno não é um tipo primitivo e sim um tipo por referência. O que possibilita que o protocolo http entenda o conteúdo 
    //do request como um Json, fazendo com que o Content-Type do cabeçalho seja referenciado como application/json.
    @GetMapping("/retornando-tipo-referencia")
    public ResponseSimples getRetornoTipoJson() {
    	
    	responseSimples.setCodido(1);
    	responseSimples.setDescricao("Retorno Json");
    	
    	return responseSimples;
    }
    
    //**** RETORNO COM RESPONSEENTITY ****
    //Neste caso o recurso retorna um objeto de tipo por referência como no exemplo acima, a diferença é que esse retorno é um tipo 
    //genérico chamado ResponseEntity, que possibilita manipular de forma mais completa o http retornado. ResponseEntity representa toda a 
    //resposta HTTP: código de status, cabeçalhos e corpo . Por isso, podemos usá-lo para configurar totalmente a resposta HTTP.
    //Se quisermos usá-lo, temos que devolvê-lo do ponto final e o Spring cuida do resto. Como resultado, podemos usar qualquer 
    //tipo como corpo de resposta. Como especificamos o status da resposta programaticamente, podemos retornar com diferentes códigos 
    //de status para diferentes cenários. Além disso, podemos definir cabeçalhos HTTP, conforme definido no exemplo abaixo.
    @GetMapping("/retornando-json-com-responseEntity")
    public ResponseEntity<ResponseSimples> getRetornoTipoJsonComResponseEntity() {
    
    	responseSimples.setCodido(1);
    	responseSimples.setDescricao("Retorno Json");
    	
    	return ResponseEntity.status(HttpStatus.OK).header("Inf", "Informacao ho Head").body(responseSimples);
    }
    
    //**** RETORNO COM RESPONSEENTITY SEM TIPO DEFINIDO  ****
    //No exemplo abaixo, usamos o retorno com o objeto ResponseEntity, que nos permite manipular o protocolo http, fornecendo 
    //maior controle sobre a resposta ao cliente. Pelo fato desse objeto ser um collection, em tese você precisaria passar o tipo que 
    //sera transitado, mas o spring nós abstrai dessa exigencia, sendo possível informar que não sabemos o tipo usando a expressão <?>.
    @GetMapping("/retornando-json-com-responseEntity-sem-tipo")
    public ResponseEntity<ResponseSimples> getRetornoTipoJsonComResponseEntitySemTipo() {
    
    	responseSimples.setCodido(1);
    	responseSimples.setDescricao("Retorno Json");
    	
    	return ResponseEntity.status(HttpStatus.OK).body(responseSimples);
    }	
}
