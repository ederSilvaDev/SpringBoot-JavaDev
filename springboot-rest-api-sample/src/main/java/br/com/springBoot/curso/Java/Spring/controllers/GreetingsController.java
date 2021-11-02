package br.com.springBoot.curso.Java.Spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springBoot.curso.Java.Spring.Java.model.usuario;
import br.com.springBoot.curso.Java.Spring.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController /* COM A UTL MAPEADA, INTERCEPTA TODAS REQUISIÇÕES VINDO O NAVEGADOR */
public class GreetingsController {
	
	@Autowired/*INJEÇÃO DE DEPENDECIAS*/
	private UsuarioRepository usuarioRepository;
	/**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/MostraNome/{name}", method = RequestMethod.GET) /*MAPEADO O PARAMETROS*/
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Curso Spring Boot API 2021 : " + name + "!";    
      
    }

	@RequestMapping(value = "/inserir/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaMundo(@PathVariable String nome){
		usuario usuario = new usuario();
		usuario.setNome(nome);
		
		usuarioRepository.save(usuario);		
		
		return "Ola Seu merda : " + nome;
    	
    }
	
	@GetMapping(value = "listatodos")
	@ResponseBody /*RETORNA OS DADOS PARA O CORPO DA RESPOTA*/
	public ResponseEntity<List<usuario>> listaUsuario(){
		
		List<usuario> usuarios = usuarioRepository.findAll();
		return new ResponseEntity<List<usuario>>(usuarios, HttpStatus.OK);
		
	}
}
