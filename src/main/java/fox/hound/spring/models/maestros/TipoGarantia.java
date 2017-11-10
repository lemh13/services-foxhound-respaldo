package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.Garantia;

@Entity
@Table(name="tipo_garantia")
@CustomJsonRootName(plural = "tipoGarantia", singular = "tipoGarantia")
public class TipoGarantia extends Maestro {

	@OneToMany(mappedBy="tipoGarantia")
	@JsonManagedReference(value="garantia-tipoGarantia")
	private List<Garantia> garantia;
	
	public TipoGarantia(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	public TipoGarantia(String id) {
		super(id);
	}
	public TipoGarantia() {
		super();
	}
	public List<Garantia> getGarantia() {
		return garantia;
	}
	public void setGarantia(List<Garantia> garantia) {
		this.garantia = garantia;
	}

}
