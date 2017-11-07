package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="cargo")
@CustomJsonRootName(plural = "cargo", singular = "cargo")
public class Cargo extends Maestro {

	public Cargo(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	
	public Cargo() {
		super();
	}
	
}
