# EndPointAPI

# Para utilizar la aplicación, es requerido tener instalado Postman, una aplicación que permite realizar peticiones POST

# Para instalar la aplicación, se debe utilizar el comando mvn clean install desde una terminal. Posterior a ello, para ejecutarla, se debe ejecutar el comando mvn spring-boot:run

# Posteriormente, en Postman se debe generar una nueva petición HTTP de tipo POST. El enlace de la peticion es http://localhost:8080/api/usuario. El body de la petición debe tener el formato que se indica a continuación.

{
    "name": "nombre_del_usuario",
    "email": "email_del_usuario, en formato xxxxx@xxxxx.xxxx",
    "password": "contraseña",
    "phones": [
        {
        "number": "1234567",
        "citycode": "1",
        "countrycode": "57"
        },
                {
        "number": "34949764",
        "citycode": "1",
        "countrycode": "56"
        },
                {
        "number": "4949798",
        "citycode": "1",
        "countrycode": "57"
        }
    ]
}

# Es posible incluir más de un teléfono en cada petición

# Al enviar la petición, se realizarán 3 validaciones

# 1. Si el correo electrónico ingresado ya se encuentra registrado, entonces se devolverá un mensaje de error
# 2. Si el correo electrónico no cumple con el formato solicitado, entonces se devolverá un mensaje de error
# 3. Si la contraseña no cumple con las condiciones que se indican a continuación, entonces se devolverá un mensaje de error.

# 3.1. Al menos 8 caracteres de longitud
# 3.2. Al menos una letra mayúscula
# 3.3. Al menos una letra minúscula
# 3.4. Al menos un dígito.
# 3.5. Al menos un carácter especial de la lista @#$%^&+=.
# 3.6. No contener espacios en blanco.

# En caso de que las 3 validaciones hayan resultado exitosas, se registrará correctamente al usuario, y se devolverá como respuesta un objeto con los siguientes datos.

# UUID del usuario
# Fecha de creación del usuario
# Fecha de la última actualización de usuario
# Fecha del último ingreso del usuario 
# token: token de acceso de la API (puede ser UUID o JWT)
# isactive: Indica si el usuario sigue habilitado dentro del sistema.