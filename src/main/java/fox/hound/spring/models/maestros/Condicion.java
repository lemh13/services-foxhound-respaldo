package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.CondicionInmueble;

@Entity
@Table(name="condicion")
@CustomJsonRootName(plural = "condicion", singular = "condicion")
public class Condicion extends Maestro {

	@OneToMany(mappedBy="condicion")
	@JsonManagedReference(value="condicionInmueble-condicion")
	private List<CondicionInmueble> condicionInmueble;
	
	public Condicion(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	public Condicion(String id) {
		super(id);
	}
	public Condicion() {
		super();
	}
	public List<CondicionInmueble> getCondicionInmueble() {
		return condicionInmueble;
	}
	public void setCondicionInmueble(List<CondicionInmueble> condicionInmueble) {
		this.condicionInmueble = condicionInmueble;
	}
	
}
