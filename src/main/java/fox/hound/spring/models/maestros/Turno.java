package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Visita;

@Entity
@Table(name="turno")
@CustomJsonRootName(plural = "turno", singular = "turno")
public class Turno extends Maestro {

	@OneToMany(mappedBy="turno")
	@JsonManagedReference(value="visita-turno")
	private List<Visita> visitas;
	
	public Turno(Long id, String name, int estatusId) {
		super(id, name, estatusId);
	}
	public Turno(String id) {
		super(id);
	}
	public Turno() {
		super();
	}
	public List<Visita> getVisitas() {
		return visitas;
	}
	public void setVisitas(List<Visita> visitas) {
		this.visitas = visitas;
	}
	
	
}
