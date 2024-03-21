package com.nisum.endpointapi.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.nisum.endpointapi.model.User;

import netscape.javascript.JSObject;


public interface UserService {


    public abstract String generarToken();
    
    public abstract boolean encontrarUsuarioPorEmail(User usuario);

    public abstract boolean esEmailValido(String email);

    public abstract boolean validarContrasena(String contrasena);

    public abstract String crearUsuario(String jsonString);

    public abstract List<User> listarUsuarios();
}
