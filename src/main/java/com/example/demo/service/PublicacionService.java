package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.Publicacion;
import com.example.demo.model.Usuario;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.example.demo.repository.PublicacionRepository;
import com.example.demo.repository.UsuarioRepository;

import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class PublicacionService {
	PublicacionRepository repo;
	UsuarioRepository repoUser;
	
	public PublicacionService(PublicacionRepository repo,UsuarioRepository repoUser) {
		this.repo = repo;
		this.repoUser = repoUser;
	}
	
	public List<Publicacion> mostrarPublis(){
		return repo.findAll();
	}
	
	public Publicacion publicar( Publicacion publi) {
		
		return repo.save(publi);
	}
	
	public void eliminarPubli(int id) {
		repo.deleteById(id);
	}
	public void actualizarPubli(int id, Publicacion publi) {
		Publicacion publiModificado = repo.findById(id).get();
		publiModificado.setTextPubli(publi.getTextPubli());
		repo.save(publiModificado);
	}
	 
	public List<Publicacion> obtenerPublisUser() {
		Long idActiva = obtenerIdUsuario();
		return repo.findByUsuario_IdUsuario(idActiva);
	}
	
	
	public Long obtenerIdUsuario() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    if (authentication != null && authentication.isAuthenticated()) {
	        Object principal = authentication.getPrincipal();
	        
	        if (principal instanceof CustomUserDetails) {
	            CustomUserDetails customUser = (CustomUserDetails) principal;
	            return customUser.getIdUsuario();  // Obtener el idUsuario desde el CustomUserDetails
	        }
	    }
	    return null;  // Si no hay usuario autenticado, retornamos null
	}

}
