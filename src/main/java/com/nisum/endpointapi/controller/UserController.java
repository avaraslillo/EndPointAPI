package com.nisum.endpointapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.nisum.endpointapi.model.User;
import com.nisum.endpointapi.repository.UserRepository;
import com.nisum.endpointapi.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
    @Qualifier("userServiceImpl")
	private UserService usuarioService;
	
	
	
	@PostMapping("/usuario")
	public String createUser(@RequestBody String jsonString) {
		
		return usuarioService.crearUsuario(jsonString);
	}
	
}

