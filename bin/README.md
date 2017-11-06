# OCELOT ![ocelot](https://gitlab.com/assets/favicon-075eba76312e8421991a0c1f89a89ee81678bcde72319dd3e8047e2a47cd3a42.ico)

![Heroku](https://dashboard.heroku.com/images/static/apple-touch-icon-120x120.png) -> [Heroku url](https://fox-hound.herokuapp.com/)
    
#### Endspoints    
1. [Usuario Endpoints](README.md#usuario-endpoints)
2. [Inmueble Endpoints](README.md#inmueble-endpoints)
3. [Mail Endpoints](README.md#mail-endpoints)
4. [PDF Endpoints](README.md#pdf-endpoints)
5. [Notificacion Endpoints](README.md#notificacion-endpoints)

## Usuario Endpoints

``GET`` /
```
Imprime 'Fox Hound Rules!', validación para saber que el servidor esta activo.
```

``GET`` /usuarios
```
Listado de usuarios.
```

``GET`` /usuario/{id}
```
Buscar un usuario por id.
```

``GET`` /usuarioPorNombre/{name}
```
Buscar un usuario por nombre.
```

``POST`` /usuario
```ruby
Inserta un usuario.
    PARAMETROS
        { "name": "Jose", "password": "1234" }  `Content-Type: application/json`
```

``PUT`` /usuario
```ruby
Modifica un usuario.
    PARAMETROS
        { "id": 1, "name": "Jose Duin", "password": "1234" }    `Content-Type: application/json`
```

``DELETE`` /usuario/{id}
```
Elimina un usuario.
```

``GET`` /login?user={username}&passw={password}
```
Genera un token de acceso.
```

## Inmueble Endpoints

``GET`` /usuario/{usuarioId}/inmueble
```
Listado de inmuebles por usuario.
```

``GET`` /inmueble/{id}
```
Buscar un inmueble por id.
```

``POST`` /usuario/{usuarioId}/inmueble
```ruby
Insertar un inmueble a un usuario.
    PARAMETROS
        { "direccion": "Somewhere" }    `Content-Type: application/json`
```

``PUT`` /usuario/{usuarioId}/inmueble
```ruby
Modifica un inmueble.
    PARAMETROS
        { "id": 1, "direccion": "Somewhere" }   `Content-Type: application/json`
```

``DELETE`` /inmueble/{id}
```
Elimina inmueble.
```

## Mail Endpoints

``POST`` /sendMail
```ruby
Envia un email a un solo correo.
    PARAMETROS
        { "message": "josemiguelduin@gmail.com" }   `Content-Type: application/json`
```

## PDF Endpoints

``GET`` /simplePDF
```ruby
Genera un PDF con el listado de usuarios.
```

## Notificacion Endpoints

``GET`` /notificacion/send
```ruby
Modo beta (se debe tener la app instalada y configurada).
```
