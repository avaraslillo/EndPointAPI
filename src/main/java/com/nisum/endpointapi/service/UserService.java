package com.nisum.endpointapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.nisum.endpointapi.model.User;

import netscape.javascript.JSObject;


public interface UserService {

    public abstract String crearUsuario(String jsonString);

    public abstract List<User> listarUsuarios();
}
