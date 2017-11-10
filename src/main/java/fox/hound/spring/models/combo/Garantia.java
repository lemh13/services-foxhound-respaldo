package fox.hound.spring.models.combo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.maestros.CondicionGarantia;
import fox.hound.spring.models.maestros.Maestro;
import fox.hound.spring.models.maestros.TipoGarantia;
import fox.hound.spring.models.puente.ServicioGarantia;

@Entity
@Table(name="garantia")
@CustomJsonRootName(plural = "garantia", singular = "garantia")
public class Garantia extends Maestro {

	@OneToMany(mappedBy="garantia")
	@JsonManagedReference(value="garantia-condicionGarantias")
	private List<CondicionGarantia> condicionGarantias;
	
	@ManyToOne
	@JoinColumn(name="tipoGarantia_id")
	@JsonBackReference(value="garantia-tipoGarantia")
	private TipoGarantia tipoGarantia;
	
	@OneToMany(mappedBy="garantia")
	@JsonManagedReference(value="garantia-servicioGarantias")
	private List<ServicioGarantia> servicioGarantias;
	
	public Garantia() {
		super();
	}
	public Garantia(Long id, String descripcion, String estatusId, String tipoGarantiaId) {
		super(id, descripcion, estatusId);
		this.tipoGarantia = new TipoGarantia(tipoGarantiaId);
	}
	public Garantia(String id) {
		super(id);
	}
	public List<CondicionGarantia> getCondicionGarantias() {
		return condicionGarantias;
	}
	public void setCondicionGarantias(List<CondicionGarantia> condicionGarantias) {
		this.condicionGarantias = condicionGarantias;
	}
	public TipoGarantia getTipoGarantia() {
		return tipoGarantia;
	}
	public void setTipoGarantia(TipoGarantia tipoGarantia) {
		this.tipoGarantia = tipoGarantia;
	}
	public List<ServicioGarantia> getServicioGarantias() {
		return servicioGarantias;
	}
	public void setServicioGarantias(List<ServicioGarantia> servicioGarantias) {
		this.servicioGarantias = servicioGarantias;
	}
	
}
