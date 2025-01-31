package com.example.demo.service;


import java.util.ArrayList;
import java.util.List;
import com.example.demo.model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {
	UsuarioRepository repo;

	
	public UsuarioService(UsuarioRepository repo) {
		this.repo = repo;
	}
	
	public List<Usuario> retornarUsers(){
		return repo.findAll();
	}
	
	public Usuario aniadirUsuario(Usuario user) {
		return repo.save(user);
	}
	
	public boolean logearUser(String nomb, String contra) {
		
		List<Usuario> usuarios = new ArrayList<>();
		repo.findAll().forEach(usuarios::add);
		for(int i=0; i < usuarios.size(); i++) {
			Usuario persona = usuarios.get(i);
			String n = persona.getNombUser();
			String c = persona.getContra();
			System.out.println(n);
			System.out.println(c);
			
			
			if(n.equals(nomb) && c.equals(contra)){
				System.out.println("LOGEADO");
				return true;
			}
		}
		System.out.println("NO LOGEADO");
		return false;
		

	}
}
