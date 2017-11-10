package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.puente.Promocion;

@Entity
@Table(name="tipo_promocion")
@CustomJsonRootName(plural = "tipoPromocion", singular = "tipoPromocion")
public class TipoPromocion extends Maestro {
	
	@OneToMany(mappedBy="tipoPromocion")
	@JsonManagedReference(value="tipoPromocion-promociones")
	private List<Promocion> promociones;

	public TipoPromocion(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	public TipoPromocion(String id) {
		super(id);
	}
	public TipoPromocion() {
		
	}
	public List<Promocion> getPromociones() {
		return promociones;
	}
	public void setPromociones(List<Promocion> promociones) {
		this.promociones = promociones;
	}

}
