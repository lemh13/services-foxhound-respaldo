package fox.hound.spring.security;

import java.io.StringReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;

import fox.hound.spring.models.Usuario;

@Component
public class TokenUtil {
		
	private final String AUDIENCE_UNKNOWN   = "unknown";
	private final String AUDIENCE_MOBILE = "mobile";
	private final String AUDIENCE_WEB    = "web";

	@Value("${foxhound.token.secret}")
	private String secret;

	@Value("${foxhound.token.expiration}")
	private Long expiration;
	
	/**
	 *  Extraemos el usuario, mediante el token pasado por parametro
	 *
	 *  @params token: token generado segun el rol
	 *  @return Useruario
	 **/
	public Usuario getUserFromToken(String token) {
		ObjectMapper mapper = new ObjectMapper();
	    String user;
	    try {
	      final Claims claims = this.getClaimsFromToken(token);
	      user = claims.getSubject();
	      return mapper.readValue(new StringReader(user), Usuario.class);
	    } catch (Exception e) {
	      return null;
	    }
	}

	/**
	 *  A partir de los datos del token,
	 *  obtenemos el nombre de usuario
	 *
	 *  @params token
	 *  @return nombre usuario
	 **/
	public String getUsernameFromToken(String token) {
	    String username;
	    try {
	      final Claims claims = this.getClaimsFromToken(token);
	      username = claims.getSubject();
	    } catch (Exception e) {
	      username = null;
	    }
	    return username;
	}

	/**
	 *  A partir de los datos del token,
	 *  obtenemos su fecha de creacion
	 *
	 *  @params token
	 *  @return fecha creacion
	 **/
	public Date getCreatedDateFromToken(String token) {
		Date created;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			created = new Date((Long) claims.get("created"));
		} catch (Exception e) {
			created = null;
		}
		return created;
	}

	/**
	 *  A partir de los datos del token,
	 *  obtenemos su fecha de expiracion
	 *
	 *  @params token
	 *  @return fecha expiracion
	 **/
	public Date getExpirationDateFromToken(String token) {
	    Date expiration;
	    try {
	      final Claims claims = this.getClaimsFromToken(token);
	      expiration = claims.getExpiration();
	    } catch (Exception e) {
	      expiration = null;
	    }
	    return expiration;
	}

	/**
	 *  Verificamos si el token que pasamos por parametro
	 *  se ha generado para la version movil.
	 *
	 *  @params token
	 *  @return token audience
	 **/
	public String getAudienceFromToken(String token) {
	    String audience;
	    try {
	      final Claims claims = this.getClaimsFromToken(token);
	      audience = (String) claims.get("audience");
	    } catch (Exception e) {
	      audience = null;
	    }
	    return audience;
	}

	/**
	 * A partir del token obtenemos el usuario,
	 * la audince y la fecha de creacion.
	 *
	 * @params token
	 * @return datos del token
	 **/
	private Claims getClaimsFromToken(String token) {
		Claims claims;
	    try {
	      claims = Jwts.parser()
	        .setSigningKey(this.secret)
	        .parseClaimsJws(token)
	        .getBody();
	    } catch (Exception e) {
	      claims = null;
	    }
	    return claims;
	}

	/**
	 * Generamos la fecha actual, medido en milisegundos
	 *
	 * @params none
	 * @return Fecha del sistema, en milisegundos
	 **/
	private Date generateCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 *  Calculamos la fecha de expiracion que tendra el token.
	 *
	 *  @params none
	 *  @return fecha de expiracion (futuro)
	 **/
	private Date generateExpirationDate() {
	    return new Date(System.currentTimeMillis() + this.expiration * 1000);
	}

	/**
	 *  Verificamos la fecha en la cual el token caduca.
	 *
	 *  @params token
	 *  @return estatus de expiracion del token
	 **/
	private Boolean isTokenExpired(String token) {
	    final Date expiration = this.getExpirationDateFromToken(token);
	    return expiration.before(this.generateCurrentDate());
	}

	/**
	 *  Verificamos el token suministrado fue hecho
	 *  para la version movil.
	 *
	 *  @params token
	 *  @return estatus de audience del token
	 **/
	private Boolean ignoreTokenExpiration(String token) {
	    String audience = this.getAudienceFromToken(token);
	    return (this.AUDIENCE_MOBILE.equals(audience));
	}
	
	/**
	 * 	Obtenemos el dispotivo con el que se realiza la peticion
	 * 
	 * 	@param device: dispositivo con el que se realizo la peticion
	 *	@return audience 
	 **/
	private String generateAudience(Device device) {
	    String audience = this.AUDIENCE_UNKNOWN;
	    if (device.isNormal()) {
	      audience = this.AUDIENCE_WEB;
	    } else if (device.isMobile()) {
	      audience = AUDIENCE_MOBILE;
	    }
	    return audience;
	  }

	/**
	 *  Ingresamos en un map, el usuario, para quien va dirigido y la fecha actual
	 *  la que se genero el token, retornando el token de acceso para dicho usuario.
	 *
	 *  @see this.generateToken()
	 *  @params userJson: json con informacion del usuario a ingresar en el sistema
	 *  @return token de acceso a la aplicacion
	 **/
	public String generateToken(String userJson, Device device) {
	    Map<String, Object> claims = new HashMap<String, Object>();
	    claims.put("sub", userJson);
	    claims.put("audience", generateAudience(device));
	    claims.put("created", this.generateCurrentDate());
	    return this.generateToken(claims);
	}

	/**
	 *  Encriptamos el contenido por medio del de HS512
	 *
	 *  @params claims: map con los datos a generar el token
	 *  @return token de acceso
	 **/
	private String generateToken(Map<String, Object> claims) {
	    return Jwts.builder()
	      .setClaims(claims)
	      .setExpiration(this.generateExpirationDate())
	      .signWith(SignatureAlgorithm.HS512, this.secret)
	      .compact();
	}

	/**
	 * Validamos la fecha de expiracion y que el token
	 * fue hecho para la version movil.
	 *
	 * @params token
	 * @return estado vigencia del token
	 **/
	public Boolean canTokenBeRefreshed(String token) {
	    return (!(this.isTokenExpired(token)) || this.ignoreTokenExpiration(token));
	}

	/**
	 * Construimos un nuevo token, prolongando el acceso
	 * al usuario en el sistema por otro periodo de tiempo.
	 *
	 * @params token
	 * @return nuevo token
	 **/
	public String refreshToken(String token) {
	    String refreshedToken;
	    try {
	      final Claims claims = this.getClaimsFromToken(token);
	      claims.put("created", this.generateCurrentDate());
	      refreshedToken = this.generateToken(claims);
	    } catch (Exception e) {
	      refreshedToken = null;
	    }
	    return refreshedToken;
	}

}
