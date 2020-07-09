package com.estudos.spring.estudos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/parametros-na-uri")
@RestController
public class RequestMappingComParametrosNaUri {
	
	//Existem várias formas de receber parâmetros, uma delas é pela propria uri do recurso. Nesse caso devemos preparar o método
	//para receber esses valores, mas para isso é necessário usar algumas anotações do spring.
	
	//**** PARAMETRO PASSADO PELA URI USANDO O CARACTER ? ****
	//Umas das formas de receber parâmetros é usando a uri. Nesse caso é possivel informa-las bastanto usar o caracter "?" seguido do nome da variável e um igual (=), 
	//logo depois o valor que o parâmetro deve receber. Para esse tipo de request, é necessário usar a anotação @RequestParam do Spring, seguido do nome do parâmetro,
	//feito isso, é necessário criar uma váriavel no metódo que irá receber o valor do parâmetro.
	@GetMapping("/request_param_simples")
	public String getComRequestParamSimples(@RequestParam("id") long id) {  
		return "Usando a anotacao RequestParam " + id; 
	}
	
	//**** MAIS DE UM PARAMETRO PASSADO PELA URI USANDO O CARACTER ? ****
	//Como no exemplo acima, a passagem de parametros usando a anotação @RequestParam possibilita o envio de quantos parâmetros forem necessários.
	//para isso basta reenviar as mesmas informações, separadas por vírgula
	@GetMapping("/request_com2_param_simples")
	public String getComDoisRequestParamSimples(@RequestParam("id") long id, @RequestParam("id2") long id2) {  
		return "Usando a anotacao RequestParam, com 2 parâmetros. Primeiro: " + id  + " Segundo: "+ id2; 
	}
	//**** PARAMETRO PASSADO PELA URI DE FORMA OPCIONAL
	//Para que o parametro seja opcional é necessário usar a anotação @RequestParam, sendo necessário
	//tambem informar com a propriedade required uma vez que por default o Spring mantém o parametro obrigatorio
	//GetMapping devolvendo uma string
	@GetMapping("/request_com_param_opcional")
	public String getComRequestParamOpcionais(@RequestParam(value="id", required=false) String id) {
		
		if (id == null) {
			return "O parametro nao foi passado.";
		}else {
			return "Usando a anotacao RequestParam, opcional Parametro " + id;
		}	
	}

	//**** MAIS DE UM PARAMETRO PASSADO PELA URI DE FORMA OPCIONAL
	//Para que o parametro seja opcional é necessário usar a anotação @RequestParam, sendo necessário
	//tambem informar com a propriedade required uma vez que por default o Spring mantém o parametro obrigatorio
	//GetMapping devolvendo uma string
	@GetMapping("/request_com_param_2opcional")
	public String getComRequestParam2Opcionais(@RequestParam(value="id", required=false) String id, @RequestParam(value="id2", required=false) String id2) {
		
		if ((id == null) && (id2 == null)) {
			return "não passou nenhum parametro";
		}else 
		if ((id != null) && (id2 == null)) {
			return "Trouxe só o primeiro parametro que é " + id;
		}else
		if ((id == null) && (id2 != null)) {
			return  "Trouxe Somente o segundo parametro que é " + id2;
		}else 
		if ((id != null) && (id2 != null)) {
			return "Usando a anotacao RequestParam, opcional Parametro parametro1 " + id + " segundo parametro " + id2;
		}else
		return "Nenhuma situacao encontrada.";
	}
	
	//**** MAIS DE UM PARAMETRO PASSADO PELA URI DE FORMA OPCIONAL
	//Para que o parametro seja opcional é necessário usar a anotação @RequestParam, sendo necessário
	//tambem informar com a propriedade required uma vez que por default o Spring mantém o parametro obrigatorio
	//GetMapping devolvendo um ResponseEntity	
	@GetMapping("/request_com_param_2opcionalComResponseEntity")
	public ResponseEntity<String> getComRequestParam2OpcionaisComResponseEntity(@RequestParam(value="id", required=false) String id, 
												@RequestParam(value="id2", required=false) String id2) {
		String retorno;
		if ((id == null) && (id2 == null)) {
			retorno = "não passou nenhum parametro";
		}else 
		if ((id != null) && (id2 == null)) {
			retorno = "Trouxe só o primeiro parametro que é " + id;
		}else
		if ((id == null) && (id2 != null)) {
			retorno =  "Trouxe Somente o segundo parametro que é " + id2;
		}else 
		if ((id != null) && (id2 != null)) {
			retorno = "Usando a anotacao RequestParam, opcional Parametro parametro1 " + id + " segundo parametro " + id2;
		}else
			retorno =  "Nenhuma situacao encontrada.";
		
		return ResponseEntity.status(HttpStatus.OK).body(retorno);
	}
	

	//**** PARAMETRO ENVIADO FAZENDO PARTE DA URI COM @PATHVARIABLE ****
	//@PathVariable é a anotação que possibilita obter o valor passado como parâmetro na uri da chamada do serviço. Mas não como um parametro que é passado 
	//apos o sinal de interrogação (?), e sim quando o valor faz parte da uri. Para utilizar, é necessário incluir o path do recurso seguido de barra (/)) 
	//e posteriormente o nome do parametro entre chaves ({}). Quando o nome do parametro interno for o mesmo do passado na anotação 
	//@GetMapping não será necesssário colocar o parametro na anotação @PathVariable, do contrario é necessário porque a variavel interna tem um nome 
	//diferente do parametro passado na uri.
	@GetMapping("/parametro-uri-path-variable/{idade}")
    public String getComGetMappingPathVariable(@PathVariable("idade") Integer parametro){

		return "Parametro passado na uri usando @PaathVariable. Valor " + parametro; 
}

	//**** MAIS DE UM PARAMETRO ENVIADO FAZENDO PARTE DA URI COM @PATHVARIABLE ****
	//@PathVariable é a anotação que possibilita obter o valor passado como parâmetro na uri da chamada do serviço. Mas não como um parametro que é passado 
	//apos o sinal de interrogação (?), e sim quando o valor faz parte da uri. Para utilizar, é necessário incluir o path do recurso seguido de barra (/)) 
	//e posteriormente o nome do parametro entre chaves ({}). Caso seja necessário envar mais de um, basta incluir os parametros da mesma forma, separando por barra (/).
	//Para cada parâmetro externo, é necessário incluir uma variável @PathVariable interna. Quando o nome do parametro interno for o mesmo do passado na anotação 
	//@GetMapping não será necesssário colocar o parametro na anotação @PathVariable, do contrario é necessário porque a variavel interna tem um nome 
	//diferente do parametro passado na uri.
	@GetMapping("/dois-parametro-uri-path-variable/{idade}/{idade2}")
    public String getComGetMappingDoisPathVariable(@PathVariable("idade") Integer parametro, @PathVariable("idade2") Integer parametro2){

		return "Parametro passado na uri usando @PaathVariable. Primeiro Valor " + parametro + " Segundo Valor " + parametro2;  
}
	


}
