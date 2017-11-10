package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.OrdenServicio;
import fox.hound.spring.models.Visita;

@Entity
@Table(name="estado_servicio")
@CustomJsonRootName(plural = "estadoServicio", singular = "estadoServicio")
public class EstadoServicio extends Maestro {

	@OneToMany(mappedBy="estadoServicio")
	@JsonManagedReference(value="ordenServicio-estadoServicio")
	private List<OrdenServicio> ordenServicios;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "estadoServicio", cascade = CascadeType.ALL)
	private Visita visita;
	
	public EstadoServicio(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	public EstadoServicio(String id) {
		super(id);
	}
	public EstadoServicio() {
		super();
	}
	public List<OrdenServicio> getOrdenServicios() {
		return ordenServicios;
	}
	public void setOrdenServicios(List<OrdenServicio> ordenServicios) {
		this.ordenServicios = ordenServicios;
	}
	public Visita getVisita() {
		return visita;
	}
	public void setVisita(Visita visita) {
		this.visita = visita;
	}
	
}
