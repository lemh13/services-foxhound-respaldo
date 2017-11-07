package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="categoria")
@CustomJsonRootName(plural = "categoria", singular = "categoria")
public class Categoria extends Maestro {

	public Categoria(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	
	public Categoria() {
		super();
	}
	
}
