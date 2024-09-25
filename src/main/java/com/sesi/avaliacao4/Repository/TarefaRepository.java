package com.sesi.avaliacao4.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sesi.avaliacao4.model.Tarefa;
import com.sesi.avaliacao4.model.Usuario;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByUsuario(Usuario usuario);
}