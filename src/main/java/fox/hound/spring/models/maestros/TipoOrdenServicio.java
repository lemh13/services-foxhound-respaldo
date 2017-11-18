package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.OrdenServicio;

@Entity
@Table(name="tipo_orden_servicop")
@CustomJsonRootName(plural = "tipoOrdenServicio", singular = "tipoOrdenServicio")
public class TipoOrdenServicio extends Maestro {
	
	@OneToMany(mappedBy="tipoOrdenServicio")
	@JsonManagedReference(value="ordenServicios-tipoOrdenServicio")
	private List<OrdenServicio> ordenServicios;

	public TipoOrdenServicio(Long id, String name, int estatusId) {
		super(id, name, estatusId);
	}
	public TipoOrdenServicio(String id) {
		super(id);
	}
	public TipoOrdenServicio() {
		
	}
	public List<OrdenServicio> getOrdenServicios() {
		return ordenServicios;
	}
	public void setOrdenServicios(List<OrdenServicio> ordenServicios) {
		this.ordenServicios = ordenServicios;
	}
	

}
