# OCELOT ![ocelot](https://gitlab.com/assets/favicon-075eba76312e8421991a0c1f89a89ee81678bcde72319dd3e8047e2a47cd3a42.ico)

![Heroku](https://dashboard.heroku.com/images/static/apple-touch-icon-120x120.png) -> [Heroku url](https://fox-hound.herokuapp.com/)
    
``OJO`` Antes de ingresar cualquier cosa, primero verificar si existen los [estatus](README.md#estatus-endpoints),
esta tabla es solo para uso interno	

#### Endspoints    
1. [Usuario Endpoints](README.md#usuario-endpoints)
2. [Inmueble Endpoints](README.md#inmueble-endpoints)
3. [Maestros Endpoints](README.md#maestro-endpoints)
4. [Mail Endpoints](README.md#mail-endpoints)
5. [PDF Endpoints](README.md#pdf-endpoints)
6. [Notificacion Endpoints](README.md#notificacion-endpoints)

## Usuario Endpoints

``GET`` /
```
Imprime 'Fox Hound Rules!', validación para saber que el servidor esta activo.
```

``GET`` /usuario/buscarTodos
```
Listado de usuarios.
```

``GET`` /usuario/buscar/{id}
```
Buscar un usuario por id.
```

``POST`` /usuario/agregar
```ruby
Inserta un usuario.
    PARAMETROS
        
```

``PUT`` /usuario/modificar
```ruby
Modifica un usuario.
    PARAMETROS
        
```

``DELETE`` /borrar/{id}
```
Elimina un usuario.
```

``GET`` /login?user={username}&passw={password}
```
Genera un token de acceso.
```

## Inmueble Endpoints

## Maestro Endpoints

### Cargo

``GET`` /cargo/buscarTodos
```
Listado de cargos.
```

``GET`` /cargo/buscar/{id}
```
Buscar un cargo por id.
```

``POST`` /cargo/agregar
```ruby
Insertar un cargo.
    PARAMETROS
        { "descripcion": "", "estatus": "" }    `Content-Type: application/json`
```

``PUT`` /cargo/modificar
```ruby
Modifica un cargo.
    PARAMETROS
        { "id": 1, "descripcion": "", "estatus": "" }   `Content-Type: application/json`
```

``DELETE`` /cargo/borrar/{id}
```
Elimina cargo.
```

Todos los demas Maestros seguiran el mismo patron: 
	``GET``		/{nombre-maestro}/buscarTodos
	``GET``		/{nombre-maestro}/buscar/{id}
	``POST``		/{nombre-maestro}/agregar
	``PUT``		/{nombre-maestro}/modificar
	``DELETE``	/{nombre-maestro}/borrar/{id}

Todos los {nombre-maestro} son:
1. cargo
2. categoria
3. categoriaInmueble
4. categoriaServicio
5. condicion
6. estado	
7. estadoServicio
8. herramienta
9. ocupacion
10. opcion
11. pregunta
12. profesion
13 redSocial
14. tarea
15. tipoCaracteristica
16. tipoCliente
17. tipoDiagnosticoVisita
18. tipoEventualidad
19. tipoGarantia
20. tipoIdentificacion
21. tipoInmueble
22. tipoMotivo
23. tipoNotificacion
24. tipoPersona
25. tipoPromocion
26. tipoReclamo
27. tipoRecurso
28. tipoRespuesta
29. tipoServicio
30. tipoVisita
31. turno
32. ubicacion
33. unidadMedida
34. usoInmueble 

### Estatus Endpoints
``GET`` /estatus/buscarTodos
```
Listado de estatus.
```

``GET`` /estatus/buscar/{id}
```
Buscar un estatus por id.
```

``POST`` /estatus/agregar
```ruby
Insertar un estatus.
    PARAMETROS
        { "descripcion": "", "estatus": "" }    `Content-Type: application/json`
```

``PUT`` /estatus/modificar
```ruby
Modifica un estatus.
    PARAMETROS
        { "id": 1, "descripcion": "", "estatus": "" }   `Content-Type: application/json`
```

``DELETE`` /estatus/borrar/{id}
```
Elimina estatus.
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
