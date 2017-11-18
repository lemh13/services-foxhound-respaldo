package fox.hound.spring.models.combo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Servicio;
import fox.hound.spring.models.maestros.CondicionGarantia;
import fox.hound.spring.models.maestros.Maestro;

@Entity
@Table(name="garantia")
@CustomJsonRootName(plural = "garantia", singular = "garantia")
public class Garantia extends Maestro {

	@OneToMany(mappedBy="garantia")
	@JsonManagedReference(value="garantia-condicionGarantias")
	private List<CondicionGarantia> condicionGarantias;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "garantia", cascade = CascadeType.ALL)
	private Servicio servicio;
	
	public Garantia() {
		super();
	}
	public Garantia(Long id, String descripcion, int estatusId) {
		super(id, descripcion, estatusId);
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
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
	
}
