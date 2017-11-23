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
@Table(name="municipio")
@CustomJsonRootName(plural = "municipio", singular = "municipio")
public class Municipio extends Maestro {
	
	@ManyToOne
	@JoinColumn(name="ciudad_id")
	@JsonBackReference(value="ciudad-municipios")
	private Ciudad ciudad;
	@Transient
	private Long padre_id;
	@Transient
	private String padre_descripcion;
	
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
	public Long getPadre_id() {
		return ciudad.getPadre_id();
	}
	public void setPadre_id(Long padre_id) {
		this.padre_id = padre_id;
	}
	public String getPadre_descripcion() {
		return ciudad.getDescripcion();
	}
	public void setPadre_descripcion(String padre_descripcion) {
		this.padre_descripcion = padre_descripcion;
	}
	public List<Parroquia> getParroquias() {
		return parroquias;
	}
	public void setParroquias(List<Parroquia> parroquias) {
		this.parroquias = parroquias;
	}
	
	
}