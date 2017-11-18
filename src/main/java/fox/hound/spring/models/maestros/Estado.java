package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.Ciudad;

@Entity
@Table(name="estado")
@CustomJsonRootName(plural = "estado", singular = "estado")
public class Estado extends Maestro {
	
	@OneToMany(mappedBy="estado")
	@JsonManagedReference(value="ciudad-estado")
	private List<Ciudad> ciudades;

	public Estado(Long id, String name, int estatusId) {
		super(id, name, estatusId);
		
	}
	public Estado(String id) {
		super(id);
	}
	public Estado() {
		super();
	}
	public List<Ciudad> getCiudades() {
		return ciudades;
	}
	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}
	
}
