package fox.hound.spring.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fox.hound.spring.models.Persona;
import fox.hound.spring.models.response.HttpResponseError;
import fox.hound.spring.security.TokenUtil;
import fox.hound.spring.services.PersonaService;
import fox.hound.spring.utils.EncryptionUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
public class LoginController {
	
    private final Logger logger = Logger.getLogger(this.getClass());
	
	@Value("${foxhound.token.header}")
    private String tokenHeader;

    @Autowired
    private TokenUtil tokenUtil;
    
    @Autowired
    private PersonaService personaService;
    
    @Autowired
	private EncryptionUtil encript;
    
    @RequestMapping(value = "/login/{user}/{passw}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> login(@PathVariable String user, @PathVariable String passw,
                                   Device device, HttpServletResponse response) {
    	Persona persona = personaService.getByEmailAndPassword(user, encript.md5( passw ));
    	if (persona != null) {
    		ObjectMapper mapper = new ObjectMapper();
            String userJson = null;
            
            // Meter los permisos y dashboard
            
            try {
                userJson = mapper.writeValueAsString(persona);
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
            persona.setPassword(null);
    	} else {
    		response.setHeader(tokenHeader, null);
    		return new ResponseEntity<>(new HttpResponseError(401, "Unauthorized", "Los datos introducidos son inválidos. Por favor intente de nuevo."), HttpStatus.NO_CONTENT);
    	}
    	logger.info("fin");
    	return ResponseDefault.ok(persona, Persona.class, ResponseDefault.PLURAL);
    	//return ResponseEntity.ok(persona);
    }

}
