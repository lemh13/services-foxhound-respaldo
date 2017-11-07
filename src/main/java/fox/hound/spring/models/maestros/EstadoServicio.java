package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="estado_servicio")
@CustomJsonRootName(plural = "estadoServicio", singular = "estadoServicio")
public class EstadoServicio extends Maestro {

	public EstadoServicio(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	
	public EstadoServicio() {
		super();
	}
	
}
