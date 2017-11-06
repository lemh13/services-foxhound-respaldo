package fox.hound.spring.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="cargo")
@CustomJsonRootName(plural = "cargo", singular = "cargo")
public class Cargo extends Maestro {

	public Cargo(Long id, String name) {
		super(id, name);
	}
	
	public Cargo() {
		super();
	}
	
}
