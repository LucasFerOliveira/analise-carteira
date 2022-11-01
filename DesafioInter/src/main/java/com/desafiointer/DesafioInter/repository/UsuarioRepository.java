package com.desafiointer.DesafioInter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafiointer.DesafioInter.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);
}
