package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Trabajador;
import fox.hound.spring.models.puente.CargoTipoServicio;

@Entity
@Table(name="cargo")
@CustomJsonRootName(plural = "cargo", singular = "cargo")
public class Cargo extends Maestro {

	@OneToMany(mappedBy="cargo")
	@JsonManagedReference(value="trabajador-cargo")
	private List<Trabajador> trabajadors;
	
	@OneToMany(mappedBy="cargo")
	@JsonManagedReference(value="cargoTipoServicio-cargo")
	private List<CargoTipoServicio> cargoTipoServicios;
	
	public Cargo(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	public Cargo(String id) {
		super(id);
	}
	public Cargo() {
		super();
	}
	public List<Trabajador> getTrabajadors() {
		return trabajadors;
	}
	public void setTrabajadors(List<Trabajador> trabajadors) {
		this.trabajadors = trabajadors;
	}
	public List<CargoTipoServicio> getCargoTipoServicios() {
		return cargoTipoServicios;
	}
	public void setCargoTipoServicios(List<CargoTipoServicio> cargoTipoServicios) {
		this.cargoTipoServicios = cargoTipoServicios;
	}
	
}
