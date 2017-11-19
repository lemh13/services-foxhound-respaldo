package fox.hound.spring.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="usuario")
//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "usuario", singular = "usuario")
public class Usuario extends Persona {

	
	public Usuario(Long id, String nombre, char sexo, String direccion, int identificacion, Date fecha_de_nacimiento,
			String telefono, int estatusId, String tipoPersonaId, String sectorId, String email, String password,
			String rolId, String tipoClienteId) {
		super(id, nombre, sexo, direccion, identificacion, fecha_de_nacimiento, telefono, estatusId, sectorId,
				email, password, rolId, Integer.valueOf(tipoPersonaId));

	}
	public Usuario(String id) {
		super(id);
	}
	public Usuario() {
		super();
	}
	
	
}
