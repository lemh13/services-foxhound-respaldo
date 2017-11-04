package fox.hound.spring.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fox.hound.spring.models.HttpResponseError;
import fox.hound.spring.models.Usuario;
import fox.hound.spring.security.TokenUtil;
import fox.hound.spring.services.UsuarioService;
import fox.hound.spring.utils.EncryptionUtil;

@RestController
public class LoginController {
	
    private final Logger logger = Logger.getLogger(this.getClass());
	
	@Value("${foxhound.token.header}")
    private String tokenHeader;

    @Autowired
    private TokenUtil tokenUtil;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
	private EncryptionUtil encript;
    
    @RequestMapping(value = "/login", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> login(@RequestParam(value = "user", required = true) String name,
                                   @RequestParam(value = "passw", required = true) String passw,
                                   Device device, HttpServletResponse response) {
    	Usuario usuario = usuarioService.getUserByUserNameAndPassword(name, encript.md5( passw ));
    	if (usuario != null) {
    		ObjectMapper mapper = new ObjectMapper();
            String userJson = null;
            
            // Meter los permisos y dashboard
            
            try {
                userJson = mapper.writeValueAsString(usuario);
            } catch (JsonProcessingException ex) {
                logger.error("Error Transformando Usuario a JSON ", ex);
                return ResponseEntity.ok(new HttpResponseError(500, "Error Interno", "Error Interno del Servidor"));
            }
            
            String token = this.tokenUtil.generateToken(userJson, device);
            response.setHeader(tokenHeader, token);
            
//            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
//            		usuario.getUserName(),
//            		usuario.getPassword()));
//            
            // Invisible a la hora de responder
            usuario.setPassword(null);
    	} else {
    		response.setHeader(tokenHeader, null);
    		return ResponseEntity.ok(new HttpResponseError(401, "Unauthorized", "Los datos introducidos son inválidos. Por favor intente de nuevo."));
    	}
    	return ResponseEntity.ok(usuario);
    }

}
