package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Publicacion;
import com.example.demo.model.Usuario;

public interface PublicacionRepository extends JpaRepository<Publicacion, Integer>{
	List<Publicacion> findByUsuario_IdUsuario(Long usuarioId);
}
