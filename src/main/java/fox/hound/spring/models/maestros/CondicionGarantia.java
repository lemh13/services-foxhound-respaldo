package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.Garantia;

@Entity
@Table(name="condicion_garantia")
@CustomJsonRootName(plural = "condicionGarantia", singular = "condicionGarantia")
public class CondicionGarantia extends Maestro {

	@ManyToOne
	@JoinColumn(name="garantia_id")
	@JsonBackReference(value="garantia-condicionGarantias")
	private Garantia garantia;
	
	public CondicionGarantia(Long id, String name, int estatusId, String garantiaId) {
		super(id, name, estatusId);
		this.garantia = new Garantia(garantiaId);
	}
	public CondicionGarantia(String id) {
		super(id);
	}
	public CondicionGarantia() {
		super();
	}
	public Garantia getGarantia() {
		return garantia;
	}
	public void setGarantia(Garantia garantia) {
		this.garantia = garantia;
	}
	
	
}
