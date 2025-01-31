package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class UsuarioController {
	
	private final UsuarioService usuarioService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	
	@GetMapping("/")
	public String inicio() {
		return "index";
	}
	
	@GetMapping("/registrarUsuario")
	public String registrarUsuario() {
		return "registrarUsuario";
	}
	
	@PostMapping("/registrarUsuario")
	public Usuario aniadirUser(@RequestBody Usuario user){
		System.out.println(user.getNombUser());
		System.out.println(user.getContra());
		user.setContra(passwordEncoder.encode(user.getContra()));
		
		
		return usuarioService.aniadirUsuario(user);
	}
	/*		
	@GetMapping("/logeo")
	public String loginPage() {
	    return "logeo"; 
	}

	@PostMapping("/logeo")
	public String iniciarSesion(@RequestParam("nomb") String nomb, @RequestParam("contra") String contra) {
		boolean logeo = usuarioService.logearUser(nomb, contra);
		
		if(logeo) {
			return "redirect:/main";
		}else {
			return "redirect:/logeo";
		}

		
	}
	*/
    @GetMapping("/usuarios")
    public String obtenerUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.retornarUsers();

        model.addAttribute("usuarios", usuarios);

        return "usuarios"; 
    }
    
}
