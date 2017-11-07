package fox.hound.spring.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fox.hound.spring.models.Usuario;
import fox.hound.spring.security.TokenUtil;
import fox.hound.spring.services.UsuarioService;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    private TokenUtil tokenUtil;

    @Value("${foxhound.token.header}")
    private String tokenHeader;
	
	private Class<?> CLASE = Usuario.class;
	
	@RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getAllUsers() {		
		return ResponseDefault.ok(usuarioService.getAllUsers(), CLASE, ResponseDefault.PLURAL);
	}
	
	@RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getUser(@PathVariable("id") String id, HttpServletRequest request) {
		Usuario u = tokenUtil.getUserFromToken(request.getHeader(tokenHeader));
	     
//		logger.info("UsuarioId: " + u.getId() + " Username: " + u.getUserName());
		
		return ResponseDefault.ok(usuarioService.getUser(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	}
	
	@RequestMapping(value="/agregar", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> addUser(@RequestBody Usuario user) {
		return ResponseDefault.ok(usuarioService.saveOrUpdateUser(user), CLASE, ResponseDefault.SINGULAR);
	}
	
	@RequestMapping(value="/modificar", method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> updateUser(@RequestBody Usuario user) {
		return ResponseDefault.ok(usuarioService.saveOrUpdateUser(user), CLASE, ResponseDefault.SINGULAR);
	}
	
	@RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> deleteUser(@PathVariable String id) {
		usuarioService.deleteUser(Long.valueOf(id));
		return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "Usuario");
	}

}
