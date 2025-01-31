package com.example.demo.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    
    private String nombUser;
    private String contra;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Publicacion> publicacion;

    public Usuario() {
    }

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombUser() {
		return nombUser;
	}

	public void setNombUser(String nombUser) {
		this.nombUser = nombUser;
	}

	public String getContra() {
		return contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}

	public List<Publicacion> getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(List<Publicacion> publicacion) {
		this.publicacion = publicacion;
	}
    
    
}
