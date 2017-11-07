package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="tipo_promocion")
@CustomJsonRootName(plural = "tipoPromocion", singular = "tipoPromocion")
public class TipoPromocion extends Maestro {

	public TipoPromocion(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	
	public TipoPromocion() {
		
	}

}
