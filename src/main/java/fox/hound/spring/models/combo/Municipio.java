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
import fox.hound.spring.models.maestros.Maestro;

@Entity
@Table(name="municipio")
@CustomJsonRootName(plural = "municipio", singular = "municipio")
public class Municipio extends Maestro {
	
	@ManyToOne
	@JoinColumn(name="ciudad_id")
	@JsonBackReference(value="ciudad-municipios")
	private Ciudad ciudad;
	
	@OneToMany(mappedBy="municipio")
	@JsonManagedReference(value="municipio-parroquias")
	private List<Parroquia> parroquias;

	public Municipio(Long id, String name, int estatusId, String ciudadId) {
		super(id, name, estatusId);
		this.ciudad = new Ciudad(ciudadId);
	}
	public Municipio(String id) {
		super(id);
	}
	public Municipio() {
		super();
	}
	public Ciudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	
}