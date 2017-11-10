package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Servicio;

@Entity
@Table(name="unidad_medida")
@CustomJsonRootName(plural = "unidadMedida", singular = "unidadMedida")
public class UnidadMedida extends Maestro {

	@OneToMany(mappedBy="unidadMedida")
	@JsonManagedReference(value="servicio-unidadMedida")
	private List<Servicio> servicios;
	
	public UnidadMedida(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	public UnidadMedida(String id) {
		super(id);
	}
	public UnidadMedida() {
		super();
	}
	public List<Servicio> getServicios() {
		return servicios;
	}
	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}
	
}
