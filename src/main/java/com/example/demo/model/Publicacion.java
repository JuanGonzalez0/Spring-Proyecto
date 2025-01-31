package com.example.demo.model;


import jakarta.persistence.*;

@Entity
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMensaje;
	
    private String textPubli;
    private String autor;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "idUsuario")
    private Usuario usuario;
    
    public Publicacion() {
    }

	public Long getIdMensaje() {
		return idMensaje;
	}

	public void setIdMensaje(Long idMensaje) {
		this.idMensaje = idMensaje;
	}

	public String getTextPubli() {
		return textPubli;
	}

	public void setTextPubli(String textPubli) {
		this.textPubli = textPubli;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
    
}
