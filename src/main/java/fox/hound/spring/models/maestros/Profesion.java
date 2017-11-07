package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="profesion")
@CustomJsonRootName(plural = "profesion", singular = "profesion")
public class Profesion extends Maestro {

	public Profesion(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	
	public Profesion() {
		super();
	}
	
}
