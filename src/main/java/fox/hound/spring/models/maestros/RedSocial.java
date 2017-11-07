package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="redSocial")
@CustomJsonRootName(plural = "redSocial", singular = "redSocial")
public class RedSocial extends Maestro {

	public RedSocial(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	
	public RedSocial() {
		super();
	}
	
}
