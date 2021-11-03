package br.com.springBoot.curso.Java.Spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@Autowired /* INJEÇÃO DE DEPENDECIAS */
	private UsuarioRepository usuarioRepository;

	/**
	 *
	 * @param name the name to greet
	 * @return greeting text
	 */
	@RequestMapping(value = "/MostraNome/{name}", method = RequestMethod.GET) /* MAPEADO O PARAMETROS */
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name) {
		return "Curso Spring Boot API 2021 : " + name + "!";

	}

	@RequestMapping(value = "/inserir/{nome}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String retornaMundo(@PathVariable String nome) {

		usuario usuario = new usuario();
		usuario.setNome(nome);

		usuarioRepository.save(usuario);

		return "Ola Seu merda : " + nome;

	}

	/* METODO DA API TESTADA PELO POSTMAN */
	@GetMapping(value = "listatodos")
	@ResponseBody /* RETORNA OS DADOS PARA O CORPO DA RESPOTA */
	public ResponseEntity<List<usuario>> listaUsuario() {

		List<usuario> usuarios = usuarioRepository.findAll();
		return new ResponseEntity<List<usuario>>(usuarios, HttpStatus.OK);

	}

	@PostMapping(value = "salvar") /* MAPEIA A URL */
	@ResponseBody /* DESCRIÇÃO DA RESPOSTA */
	public ResponseEntity<usuario> salvar(@RequestBody usuario usuario) {

		usuario user = usuarioRepository.save(usuario);

		return new ResponseEntity<usuario>(user, HttpStatus.CREATED);

	}

	@PutMapping(value = "atualizar") /* MAPEIA A URL */
	@ResponseBody /* DESCRIÇÃO DA RESPOSTA */
	public ResponseEntity<?> atualizar(@RequestBody usuario usuario) {
		/* RETORNA O USUARIO */
		/* QUAL É USUARIO -> O CODIGO DO USUARIO RECEBIDO POR PARAMENTROS */

		if (usuario.getId() == null) {
			return new ResponseEntity<String>("Id não informado", HttpStatus.OK);

		}
		usuario user = usuarioRepository.save(usuario);
		/* PEQUISA NO BANCO DE DADOS */
		return new ResponseEntity<usuario>(user, HttpStatus.OK);
		/* RETORNO PRA TELA */

	}

	@DeleteMapping(value = "delete") /* MAPEIA A URL */
	@ResponseBody /* DESCRIÇÃO DA RESPOSTA */
	public ResponseEntity<String> delete(@RequestParam Long iduser) {

		usuarioRepository.deleteById(iduser);

		return new ResponseEntity<String>("Deletado com Sucesso", HttpStatus.OK);

	}

	@GetMapping(value = "buscaruserid") /* MAPEIA A URL */
	@ResponseBody /* DESCRIÇÃO DA RESPOSTA */
	public ResponseEntity<usuario> buscaruserid(@RequestParam(name = "iduser") Long iduser) {
		/* RETORNA O USUARIO */
		/* QUAL É USUARIO -> O CODIGO DO USUARIO RECEBIDO POR PARAMENTROS */

		usuario usuario = usuarioRepository.findById(iduser).get();
		/* PEQUISA NO BANCO DE DADOS */

		return new ResponseEntity<usuario>(usuario, HttpStatus.OK);
		/* RETORNO PRA TELA */

	}
	
	@GetMapping(value = "buscarpornome") /* MAPEIA A URL */
	@ResponseBody /* DESCRIÇÃO DA RESPOSTA */
	public ResponseEntity<List<usuario>> buscarpornome(@RequestParam(name = "name") String name) {
		/* RETORNA UMA LISTA DE USUARIOS */  /* QUAL É USUARIO -> O NOME DO USUARIO RECEBIDO POR PARAMENTROS */

		List<usuario> usuario = usuarioRepository.buscarporNome(name.trim().toUpperCase());
		/* PEQUISA NO BANCO DE DADOS */

		return new ResponseEntity<List<usuario>>(usuario, HttpStatus.OK);
		/* RETORNO PRA TELA */

	}
}
