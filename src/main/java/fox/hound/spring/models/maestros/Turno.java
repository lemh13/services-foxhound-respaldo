package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="turno")
@CustomJsonRootName(plural = "turno", singular = "turno")
public class Turno extends Maestro {

	public Turno(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	
	public Turno() {
		super();
	}
	
}
