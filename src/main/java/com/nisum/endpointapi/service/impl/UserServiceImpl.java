package com.nisum.endpointapi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.nisum.endpointapi.model.Telephone;
import com.nisum.endpointapi.model.User;
import com.nisum.endpointapi.repository.UserRepository;
import com.nisum.endpointapi.service.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userRepository")
    UserRepository usuarioRepository;

    public boolean encontrarUsuarioPorEmail(User usuario){
        if(usuarioRepository.findByEmail(usuario.getEmail()).isEmpty()){
            return false;
        }

        else{
            return true;
        }
    }

    @Override
    public JSONPObject crearUsuario(String jsonString) {
        JSONPObject objetoRetorno= new JSONPObject(jsonString, jsonString);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            System.out.println(jsonString);
            System.out.println(jsonNode.toString());
            String name=jsonNode.get("name").asText();
            System.out.println(name);
            String email=jsonNode.get("email").asText();
            String password=jsonNode.get("password").asText();
            
            JsonNode phones = jsonNode.get("phones");
            //String telefonoString = objectMapper.writeValueAsString(phones);
            //String[] array = objectMapper.readValue(telefonoString, String[].class);
            List <Telephone> lista_telefonos=new ArrayList<Telephone>();
            /*System.out.println("Este es un array:");
            System.out.println(array);
            for(int i = 0; i < array.length ; i++){
                System.out.println(array[i]);
                JsonNode phoneNode = objectMapper.readTree(array[i]);
                String phone_number=phoneNode.get("phone_number").asText();
                String phone_citycode=phoneNode.get("phone_citycode").asText();
                String phone_countrycode=phoneNode.get("phone_countrycode").asText();
                Telephone telephone = new Telephone(phone_number,phone_citycode,phone_countrycode);
                lista_telefonos.add(telephone);

                //System.out.println(phone_number);
            }
            System.out.println("Estos son los teléfonos:");
            System.out.println(lista_telefonos.toString());*/

            Date created=new Date();
            Date modified=new Date();
            Date last_login=new Date();
            String token="";
            boolean is_active=true;

            User usuario=new User(name,email,password,null,created,modified,last_login,token,is_active);

            System.out.println(usuario.toString());

            if(encontrarUsuarioPorEmail(usuario)==true){
                System.out.println("El correo ya está registrado");
            }

            else{
                User usuario_guardado=usuarioRepository.save(usuario);

                System.out.println(usuario_guardado.toString());
            }


            
            /*Usuario usuario_guardado=usuarioRepository.save(usuario);*/
        // TODO Auto-generated method stub
        }catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            return objetoRetorno;
        }
    }

    @Override
    public List<User> listarUsuarios() {
        // TODO Auto-generated method stub
        return (ArrayList<User>) usuarioRepository.findAll();
    }

}
