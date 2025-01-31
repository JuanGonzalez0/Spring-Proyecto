package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Publicacion;
import com.example.demo.model.Usuario;
import com.example.demo.service.PublicacionService;
import com.example.demo.repository.UsuarioRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.demo.repository.UsuarioRepository;

@Controller
public class PublicacionController {
	
	private final PublicacionService publicacionService;
	private final UsuarioRepository usuarioRepository;
	
	
	public PublicacionController(PublicacionService publicacionService,UsuarioRepository usuarioRepository) {
		this.publicacionService = publicacionService;
		this.usuarioRepository = usuarioRepository;
	}
	
	@GetMapping("/main")
	public String mostrarPubli(Publicacion publi, Model model, Usuario user) {
		List<Publicacion> publis = publicacionService.mostrarPublis();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String username = authentication.getName();
		
		model.addAttribute("username", username);
		
		model.addAttribute("publis",publis);
		
		return "main";
	}
	
	@PostMapping("/main")
	public Publicacion publicar(@RequestBody Publicacion publi) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String username = authentication.getName();
		Long idUser = publicacionService.obtenerIdUsuario();
        
		System.out.println(idUser);
		publi.setAutor(username);
	    // Obtener el objeto Usuario usando el idUsuario
	    Usuario usuario = usuarioRepository.findByIdUsuario(idUser);

	    // Asignar el objeto Usuario completo a la Publicacion
	    publi.setUsuario(usuario);
		
		return publicacionService.publicar(publi);
	}
	
	@GetMapping("/publiUser")
	public String publicacionesUsuario(Model model) {
		
		List<Publicacion> publisUser = publicacionService.obtenerPublisUser();
		
		model.addAttribute("publisUser", publisUser);
		
		
		return "publiUser";
	}

	
	@DeleteMapping("/publiUser/{id}")
	public void eliminarPublicacion(@PathVariable int id) {
		publicacionService.eliminarPubli(id);
	}
	
	@PutMapping("/cambiarPubli/{id}")
	public ResponseEntity<String> cambiarPublicacion(@PathVariable int id,@RequestBody Publicacion publi) {
		publicacionService.actualizarPubli(id, publi);
		return ResponseEntity.ok("Publicación actualizada correctamente");
	}
	
}
