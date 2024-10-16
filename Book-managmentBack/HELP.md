# Book Management API

Este proyecto es una API de gestión de libros desarrollada con **Spring Boot**. La aplicación permite a los usuarios autenticarse, registrar cuentas, gestionar sus libros favoritos, y proteger las rutas usando **JWT (JSON Web Token)** para la autenticación.

## Características

- Autenticación de usuarios con **JWT**.
- Registro y login de usuarios.
- Gestión de libros favoritos para cada usuario autenticado.
- Seguridad de rutas con **Spring Security**.
- Soporte para **CORS** para permitir el frontend alojado en `http://localhost:5173`.

## Requisitos

- Java 17+
- Spring Boot 3.x
- Maven
- Base de datos Mysql

## Configuración

### Variables de entorno
Configura las siguientes variables de entorno en tu archivo `application.properties` o `application.yml`:

```properties
jwt.secret=TuClaveSecretaJWT
jwt.expirationTime=86400000 # Tiempo en milisegundos (1 día)
```

### Dependencias principales

Las dependencias clave utilizadas en el proyecto incluyen:

- **Spring Boot Starter Web**: Para crear la API REST.
- **Spring Boot Starter Security**: Para la autenticación y autorización.
- **Spring Boot Starter JPA**: Para interactuar con la base de datos.
- **JWT (io.jsonwebtoken)**: Para la generación y validación de tokens.
- **Mysql Driver**: Para la correcta funcionalidad de la base de datos. 

## Endpoints de la API

### Autenticación

- **POST /auth/register**: Registrar un nuevo usuario.
    - Request body:
      ```json
      {
        "username": "nuevoUsuario",
        "password": "contraseñaSegura"
      }
      ```

- **POST /auth/login**: Autenticar un usuario y obtener un token JWT.
    - Request body:
      ```json
      {
        "username": "nombreUsuario",
        "password": "contraseña"
      }
      ```

    - Respuesta exitosa:
      ```json
      {
        "token": "token_jwt_generado"
      }
      ```

### Libros

- **GET /books/favorites**: Obtener los libros favoritos del usuario autenticado.
    - Requiere autenticación con un token JWT en el encabezado `Authorization`:
      ```
      Authorization: Bearer <token_jwt>
      ```

- **POST /books/favorites**: Añadir un nuevo libro a los favoritos del usuario.
    - Requiere autenticación con JWT.
    - Request body:
      ```json
      {
        "title": "Nombre del libro",
        "author": "Autor del libro"
      }
      ```

## Seguridad

La aplicación utiliza **Spring Security** para proteger las rutas y **JWT** para la autenticación. Solo las rutas `/auth/**` están abiertas sin autenticación. Todas las demás rutas requieren un token válido.

El filtro **JWTFilter** intercepta las solicitudes y verifica la validez del token JWT antes de permitir el acceso a las rutas protegidas.

## Ejecución

Para ejecutar la aplicación localmente:

1. Clona el repositorio:
   ```bash
   git clone https://github.com/abde7h/BookManagementBackend.git
   cd BookManagementBackend
   ```

2. Ejecuta la aplicación con Maven:
   ```bash
   mvn spring-boot:run
   ```

La API estará disponible en `http://localhost:8081`. (Puedes canbiar el puerto en application.properties así como el username y password para usar tu bbdd)

## Notas adicionales

- El proyecto ya incluye la configuración básica de **CORS** para permitir solicitudes desde `http://localhost:5173`, que puede ser útil para un frontend desarrollado con herramientas como **Vite** o **React**.
- La autenticación se basa en la implementación de **Spring Security** y un sistema de tokens JWT para gestionar las sesiones sin estado.


