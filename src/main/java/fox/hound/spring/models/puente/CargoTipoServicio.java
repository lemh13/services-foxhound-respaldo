package fox.hound.spring.models.puente;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.maestros.Cargo;
import fox.hound.spring.models.maestros.TipoServicio;

//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "cargoTipoServicio", singular = "cargoTipoServicio")
@Entity
@Table(name="cargo_tipo_servicio")
public class CargoTipoServicio extends Puente {

	@ManyToOne
	@JoinColumn(name="cargo_id")
	@JsonBackReference(value="cargoTipoServicio-cargo")
	private Cargo cargo;
	
	@ManyToOne
	@JoinColumn(name="tipoServicio_id")
	@JsonBackReference(value="cargoTipoServicio-tipoServicio")
	private TipoServicio tipoServicio;
	
	public CargoTipoServicio(Long id, String estatusId, String cargo, String tipoServicio) {
		super(id, estatusId);
		this.cargo = new Cargo(cargo);
		this.tipoServicio = new TipoServicio(tipoServicio);
	}
	public CargoTipoServicio(String id) {
		super(id);
	}
	public CargoTipoServicio() {
		
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	
}
