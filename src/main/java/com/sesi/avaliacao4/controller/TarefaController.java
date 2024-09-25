package com.sesi.avaliacao4.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.sesi.avaliacao4.Repository.CategoriaRepository;
import com.sesi.avaliacao4.Repository.TarefaRepository;
import com.sesi.avaliacao4.Repository.UsuarioRepository;
import com.sesi.avaliacao4.model.Tarefa;
import com.sesi.avaliacao4.model.Usuario;

import jakarta.servlet.http.HttpSession;

public class TarefaController {

	 @Autowired
	    private TarefaRepository tarefaRepository;

	    @Autowired
	    private CategoriaRepository categoriaRepository;

	    @Autowired
	    private UsuarioRepository usuarioRepository;

	    @GetMapping("/tarefas")
	    public String listarTarefas(HttpSession session, Model model) {
	        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
	        
	        if (usuarioLogado != null) {
	            List<Tarefa> tarefas = tarefaRepository.findByUsuario(usuarioLogado);
	            model.addAttribute("tarefas", tarefas);
	            
	            return "tarefas";
	        }
	        
	        return "redirect:/login";
	    }

	    @GetMapping("/tarefa/nova")
	    public String mostrarFormularioNovaTarefa(Model model) {
	        model.addAttribute("tarefa", new Tarefa());
	        model.addAttribute("categorias", categoriaRepository.findAll());
	        return "nova-tarefa";
	    }

	    @PostMapping("/tarefa/nova")
	    public String adicionarTarefa(@ModelAttribute Tarefa tarefa, HttpSession session) {
	        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
	        if (usuarioLogado != null) {
	            tarefa.setUsuario(usuarioLogado);
	            tarefaRepository.save(tarefa);
	            return "redirect:/tarefas";
	        }
	        return "redirect:/login";
	    }

	    @GetMapping("/tarefa/excluir/{id}")
	    public String excluirTarefa(@PathVariable Long id, HttpSession session) {
	        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
	        if (usuarioLogado != null) {
	            Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
	            if (tarefa != null && tarefa.getUsuario().equals(usuarioLogado)) {
	                tarefaRepository.delete(tarefa);
	            }
	        }
	        return "redirect:/tarefas";
	    }
	
	
}
