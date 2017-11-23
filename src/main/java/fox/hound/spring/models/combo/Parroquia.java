package fox.hound.spring.models.combo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	@Transient
	private Long padre_id;
	@Transient
	private String padre_descripcion;
	
	@OneToMany(mappedBy="parroquia")
	@JsonManagedReference(value="sectores-parroquias")
	private List<Sector> sectores;

	public Parroquia(Long id, String name, int estatusId, String municipioId) {
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
	public Long getPadre_id() {
		return municipio.getId();
	}
	public void setPadre_id(Long padre_id) {
		this.padre_id = padre_id;
	}
	public String getPadre_descripcion() {
		return municipio.getDescripcion();
	}
	public void setPadre_descripcion(String padre_descripcion) {
		this.padre_descripcion = padre_descripcion;
	}
	
	
}