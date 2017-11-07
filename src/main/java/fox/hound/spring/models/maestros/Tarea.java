package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="tarea")
@CustomJsonRootName(plural = "tarea", singular = "tarea")
public class Tarea extends Maestro {

	public Tarea(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	
	public Tarea() {
		super();
	}
	
}
