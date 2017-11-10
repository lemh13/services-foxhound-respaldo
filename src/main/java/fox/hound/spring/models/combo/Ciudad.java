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
	
	@OneToMany(mappedBy="ciudad")
	@JsonManagedReference(value="ciudad-municipios")
	private List<Municipio> municipios;

	public Ciudad(Long id, String name, String estatusId, String estadoId) {
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
	
}