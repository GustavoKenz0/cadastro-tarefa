package com.sesi.avaliacao4.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sesi.avaliacao4.Repository.UsuarioRepository;
import com.sesi.avaliacao4.model.Usuario;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {
	
	 @Autowired
	    private UsuarioRepository usuarioRepository;

	    @GetMapping("/cadastrar")
	    public String mostrarFormularioCadastro(Model model) {
	        model.addAttribute("usuario", new Usuario());
	        return "cadastrar";
	    }

	    @PostMapping("/cadastrar")
	    public String cadastrarUsuario(@ModelAttribute Usuario usuario) {
	        usuarioRepository.save(usuario);
	        return "redirect:/login";
	    }

	    @GetMapping("/login")
	    public String mostrarFormularioLogin(Model model) {
	        model.addAttribute("usuario", new Usuario());
	        return "login";
	    }

	    @PostMapping("/login")
	    public String logarUsuario(@ModelAttribute Usuario usuario, Model model, HttpSession session) {
	        Usuario usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
	        
	        if (usuarioExistente != null && usuarioExistente.getSenha().equals(usuario.getSenha())) {
	            session.setAttribute("usuarioLogado", usuarioExistente);
	            
	            return "redirect:/tarefas";
	        }
	        
	        model.addAttribute("erro", "Email ou senha inválidos");
	        return "login"; 
	    }
		
	
	
}
