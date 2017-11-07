package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="tipo_notificacion")
@CustomJsonRootName(plural = "tipoNotificacion", singular = "tipoNotificacion")
public class TipoNotificacion extends Maestro {

	public TipoNotificacion(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	
	public TipoNotificacion() {
		
	}

}
