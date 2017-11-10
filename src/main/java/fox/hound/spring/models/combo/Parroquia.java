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
@Table(name="parroquia")
@CustomJsonRootName(plural = "parroquia", singular = "parroquia")
public class Parroquia extends Maestro {
	
	@ManyToOne
	@JoinColumn(name="municipio_id")
	@JsonBackReference(value="municipio-parroquias")
	private Municipio municipio;
	
	@OneToMany(mappedBy="parroquia")
	@JsonManagedReference(value="sectores-parroquias")
	private List<Sector> sectores;

	public Parroquia(Long id, String name, String estatusId, String municipioId) {
		super(id, name, estatusId);
		this.municipio = new Municipio(municipioId);
	}
	public Parroquia(String id) {
		super(id);
	}
	public Parroquia() {
		super();
	}
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	public List<Sector> getSectores() {
		return sectores;
	}
	public void setSectores(List<Sector> sectores) {
		this.sectores = sectores;
	}
	
}