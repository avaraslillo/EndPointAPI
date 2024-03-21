package com.nisum.endpointapi.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;

import com.nisum.endpointapi.model.Telephone;
import com.nisum.endpointapi.model.User;
import com.nisum.endpointapi.repository.UserRepository;
import com.nisum.endpointapi.service.impl.UserServiceImpl;

public class UserServiceImplTest {

    @Mock
    @Qualifier("userRepository")
    private UserRepository usuarioRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User usuario;
    private List<Telephone> lista_telefono;
    
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        usuario = new User();
        usuario.setName("René García");
        usuario.setEmail("rene@garcia.com");
        usuario.setPassword("MusiNisum125#");
        usuario.setIs_active(true);
        usuario.setCreated(new Date());
        usuario.setModified(new Date());
        usuario.setLast_login(new Date());

        lista_telefono=new ArrayList<Telephone>();
        lista_telefono.add(new Telephone("1234567","1","56",usuario));
        lista_telefono.add(new Telephone("7654321","1","56",usuario));
        
        usuario.setPhone_list(lista_telefono);
    }

    @SuppressWarnings("null")
    @Test
    void testCrearUsuario() {
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        assertNotNull(usuario);
    }

    @Test
    void testEncontrarUsuarioPorEmail_RetornaVerdaderoCuandoExiste() {
        List<User> userList = new ArrayList<>();
        userList.add(usuario);
        when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(userList);

        boolean result = userService.encontrarUsuarioPorEmail(usuario);

        // Then
        assertTrue(result);
    }

    @Test
    void testEncontrarUsuarioPorEmail_RetornaFalsoCuandoNoExiste() {
        List<User> userList = new ArrayList<>();
        userList.add(usuario);
        when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(new ArrayList<User>());

        boolean result = userService.encontrarUsuarioPorEmail(usuario);

        // Then
        assertFalse(result);
    }   

    @Test
    void testEsEmailValido() {
        String email=usuario.getEmail();
        assertEquals(true, userService.esEmailValido(email));
    }

    @Test
    void testGenerarToken() {
        String token = userService.generarToken();
        assertNotNull(token);
        assertTrue(token.matches("[a-f0-9]{8}-([a-f0-9]{4}-){3}[a-f0-9]{12}"));
    }

    @Test
    void testListarUsuarios() {
        List<User> listaUsuarios=new ArrayList<User>();
        listaUsuarios.add(usuario);
        when(usuarioRepository.findAll()).thenReturn(listaUsuarios);
        assertNotNull(userService.listarUsuarios());
    }

    @Test
    void testValidarContrasena() {
        String contrasenia=usuario.getPassword();
        assertEquals(true, userService.validarContrasena(contrasenia));
    }
}
