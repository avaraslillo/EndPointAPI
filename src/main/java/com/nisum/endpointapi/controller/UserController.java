package com.nisum.endpointapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	
	
	/*@GetMapping("/usuarios")
	public List<Usuario> findAllUsers(){
		return usuarioRepository.findAll();
	}
	
	@GetMapping("/usuario/{name}")
	public List<Usuario> findByName(@PathVariable("name") String name) {
		return usuarioRepository.findByName(name);
	}*/
	
	@PostMapping("/usuario")
	public String createUser(@RequestBody String jsonString) {
		return usuarioService.crearUsuario(jsonString);
	}
	
	/*@PutMapping("/usuario/{id}")
	public Usuario updateUser(@PathVariable int id ,@RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	@DeleteMapping("/usuario/{id}")
	public void deletePerson(@PathVariable("id") Long id) {
		usuarioRepository.deleteById(id);
	}*/
}

