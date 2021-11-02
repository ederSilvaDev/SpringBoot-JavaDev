package br.com.springBoot.curso.Java.Spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springBoot.curso.Java.Spring.Java.model.usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<usuario, Long>{

}
