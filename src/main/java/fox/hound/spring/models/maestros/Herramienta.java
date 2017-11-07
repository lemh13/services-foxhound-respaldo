package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="herramienta")
@CustomJsonRootName(plural = "herramienta", singular = "herramienta")
public class Herramienta extends Maestro {

	public Herramienta(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	
	public Herramienta() {
		super();
	}
	
}
