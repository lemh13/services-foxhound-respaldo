package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Servicio;
import fox.hound.spring.models.puente.CargoTipoServicio;

@Entity
@Table(name="tipo_servicio")
@CustomJsonRootName(plural = "tipoServicio", singular = "tipoServicio")
public class TipoServicio extends Maestro {

	@Column(nullable = false)
    private String imagenServicio;
	
	@OneToMany(mappedBy="tipoServicio")
	@JsonManagedReference(value="servicio-tipoServicio")
	private List<Servicio> servicios;
	
	@OneToMany(mappedBy="tipoServicio")
	@JsonManagedReference(value="cargoTipoServicio-tipoServicio")
	private List<CargoTipoServicio> cargoTipoServicios;
	
	public TipoServicio(Long id, String name, String estatusId, String imagenServicio) {
		super(id, name, estatusId);
		this.imagenServicio = imagenServicio;
	}
	public TipoServicio(String id) {
		super(id);
	}
	public TipoServicio() {
		
	}
	public String getImagenServicio() {
		return imagenServicio;
	}
	public void setImagenServicio(String imagenServicio) {
		this.imagenServicio = imagenServicio;
	}
	public List<Servicio> getServicios() {
		return servicios;
	}
	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}
	public List<CargoTipoServicio> getCargoTipoServicios() {
		return cargoTipoServicios;
	}
	public void setCargoTipoServicios(List<CargoTipoServicio> cargoTipoServicios) {
		this.cargoTipoServicios = cargoTipoServicios;
	}

}
