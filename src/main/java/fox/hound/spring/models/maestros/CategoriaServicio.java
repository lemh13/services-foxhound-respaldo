package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="categoria_servicio")
@CustomJsonRootName(plural = "categoriaServicio", singular = "categoriaServicio")
public class CategoriaServicio extends Maestro {

	public CategoriaServicio(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	
	public CategoriaServicio() {
		super();
	}
	
}
