package br.com.springBoot.curso.Java.Spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springBoot.curso.Java.Spring.Java.model.usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<usuario, Long>{
	
	@Query(value = "select u from usuario u where upper(trim(u.nome)) like %?1%")
	List<usuario> buscarporNome(String name);
	/*RETORNA UMA LISTA DE USUARIOS*/	

}
