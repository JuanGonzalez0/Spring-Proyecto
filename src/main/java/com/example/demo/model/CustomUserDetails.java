package com.example.demo.model;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.Collections;

//clase que se usara para la funcion obtenerId en service. esto para obtener el objeto usuario de la sesion actual

public class CustomUserDetails implements UserDetails {

    private String nombUser;
    private String contrasena;
    private Long idUsuario;  // Agregar el idUsuario

    // Constructor
    public CustomUserDetails(Usuario usuario) {
        this.nombUser = usuario.getNombUser();
        this.contrasena = usuario.getContra();
        this.idUsuario = usuario.getIdUsuario();  // Suponiendo que tienes este campo en la entidad Usuario
    }

    // Métodos de UserDetails
    @Override
    public String getUsername() {
        return nombUser;
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();  // Sin roles
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Getter para el idUsuario
    public Long getIdUsuario() {
        return idUsuario;
    }
}
