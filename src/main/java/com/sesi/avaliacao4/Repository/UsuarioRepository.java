package com.sesi.avaliacao4.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sesi.avaliacao4.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	Usuario findByEmail(String email);
}

