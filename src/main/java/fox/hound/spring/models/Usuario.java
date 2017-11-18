package fox.hound.spring.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.maestros.TipoCliente;
import fox.hound.spring.models.puente.ClienteOcupacion;
import fox.hound.spring.models.puente.ClienteProfesion;
import fox.hound.spring.models.puente.PreferenciaCliente;

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
