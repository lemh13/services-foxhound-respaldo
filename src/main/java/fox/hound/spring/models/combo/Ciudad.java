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
import fox.hound.spring.models.maestros.Estado;
import fox.hound.spring.models.maestros.Maestro;

@Entity
@Table(name="ciudad")
@CustomJsonRootName(plural = "ciudad", singular = "ciudad")
public class Ciudad extends Maestro {
	
	@ManyToOne
	@JoinColumn(name="estado_id")
	@JsonBackReference(value="ciudad-estado")
	private Estado estado;
	@Transient
	private Long padre_id;
	@Transient
	private String padre_descripcion;
	
	@OneToMany(mappedBy="ciudad")
	@JsonManagedReference(value="ciudad-municipios")
	private List<Municipio> municipios;

	public Ciudad(Long id, String name, int estatusId, String estadoId) {
		super(id, name, estatusId);
		this.estado = new Estado(estadoId);
	}
	public Ciudad(String id) {
		super(id);
	}
	public Ciudad() {
		super();
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public List<Municipio> getMunicipios() {
		return municipios;
	}
	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}
	public Long getEstado_id() {
		return estado.getId();
	}
	public void setEstado_id(Long estado_id) {
		this.padre_id = estado_id;
	}
	public String getEstado_descripcion() {
		return estado.getDescripcion();
	}
	public void setEstado_descripcion(String estado_descripcion) {
		this.padre_descripcion = estado_descripcion;
	}
	
	
}