package com.nisum.endpointapi.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.endpointapi.model.Telephone;
import com.nisum.endpointapi.model.User;
import com.nisum.endpointapi.repository.UserRepository;
import com.nisum.endpointapi.service.UserService;

import netscape.javascript.JSObject;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    private static final String EXPRESION_REGULAR="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    
    @Autowired
    @Qualifier("userRepository")
    UserRepository usuarioRepository;

    public boolean encontrarUsuarioPorEmail(User usuario){
        List<User> lista_usuarios=usuarioRepository.findByEmail(usuario.getEmail());
        if(lista_usuarios.isEmpty()){
            return false;
        }

        else{
            return true;
        }
    }

    public static boolean esEmailValido(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validarContrasena(String contrasena) {
        // Definir la expresión regular para la contraseña
        String expresionRegular = EXPRESION_REGULAR;

        // Compilar la expresión regular en un patrón
        Pattern pattern = Pattern.compile(expresionRegular);

        // Verificar si la contraseña coincide con el patrón
        Matcher matcher = pattern.matcher(contrasena);

        return matcher.matches();
    }

    @Override
    public String crearUsuario(String jsonString) {
        Map<String, String> map_respuesta = new HashMap<>();
        String string_retorno="";
        try {

            //String jsonStr = mapper.writeValueAsString();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            System.out.println(jsonString);
            System.out.println(jsonNode.toString());
            String name=jsonNode.get("name").asText();
            System.out.println(name);
            String email=jsonNode.get("email").asText();
            String password=jsonNode.get("password").asText();
            
            Date created=new Date();
            Date modified=new Date();
            Date last_login=new Date();
            String token="";
            boolean is_active=true;

            User usuario=new User(name,email,password,null,created,modified,last_login,token,is_active);
            JsonNode phones = jsonNode.get("phones");
            //Telephone[] array_telefonos = objectMapper.readValue(phonesString, Telephone[].class);

            List<Telephone> lista_telefonos= new ArrayList <Telephone> ();
            for(JsonNode phone: phones){
                String number = phone.get("number").asText();
                String citycode = phone.get("citycode").asText();
                String countrycode = phone.get("countrycode").asText();
                Telephone telefono = new Telephone(number, citycode, countrycode);
                telefono.setUsuario(usuario);
                lista_telefonos.add(telefono);
            }

            usuario.setPhone_list(lista_telefonos);

            //System.out.println(lista_telefonos.toString());
            



            //System.out.println(usuario.toString());

            if(encontrarUsuarioPorEmail(usuario)==true){

                System.out.println("El correo ya está registrado");
                map_respuesta.put("mensaje", "El correo ya está registrado");
                //objetoRetorno.
            }

            else if(esEmailValido(usuario.getEmail())==false){
                System.out.println("El correo no está formateado correctamente");
                map_respuesta.put("mensaje", "El correo no está formateado correctamente");
            }

            else if(validarContrasena(usuario.getPassword())==false){
                System.out.println("La contraseña debe cumplir con las siguientes condiciones: \r\n "+
                                        "Al menos 8 caracteres de longitud.\r\n" + //
                                        "Al menos una letra mayúscula.\r\n" + //
                                        "Al menos una letra minúscula.\r\n" + //
                                        "Al menos un dígito.\r\n" + //
                                        "Al menos un carácter especial de la lista @#$%^&+=.\r\n" + //
                                        "No contener espacios en blanco.");
                map_respuesta.put("mensaje", "La contraseña debe cumplir con las siguientes condiciones: \r\n "+
                                                    "Al menos 8 caracteres de longitud.\r\n" + //
                                                    "Al menos una letra mayúscula.\r\n" + //
                                                    "Al menos una letra minúscula.\r\n" + //
                                                    "Al menos un dígito.\r\n" + //
                                                    "Al menos un carácter especial de la lista @#$%^&+=.\r\n" + //
                                                    "No contener espacios en blanco.");                         
            }

            else{
                User usuario_guardado=usuarioRepository.save(usuario);

                map_respuesta.put("id",usuario_guardado.getUuid().toString());
                map_respuesta.put("created",usuario_guardado.getCreated().toString());
                map_respuesta.put("modified",usuario_guardado.getModified().toString());
                map_respuesta.put("last_login",usuario_guardado.getLast_login().toString());
                map_respuesta.put("token",usuario_guardado.getToken());
                map_respuesta.put("is_active",usuario_guardado.isIs_active() ? "true" : "false" );
            }

            string_retorno = new ObjectMapper().writeValueAsString(map_respuesta);
            
            /*Usuario usuario_guardado=usuarioRepository.save(usuario);*/
        // TODO Auto-generated method stub
        }catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            map_respuesta.put("mensaje", e.getLocalizedMessage());
            string_retorno = new ObjectMapper().writeValueAsString(map_respuesta);
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            map_respuesta.put("mensaje", e.getLocalizedMessage());
            string_retorno = new ObjectMapper().writeValueAsString(map_respuesta);
        }finally{
            
            return string_retorno;
            //return objetoRetorno;
        }
    }

    @Override
    public List<User> listarUsuarios() {
        // TODO Auto-generated method stub
        return (ArrayList<User>) usuarioRepository.findAll();
    }

}
