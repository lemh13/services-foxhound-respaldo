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
import fox.hound.spring.models.maestros.TipoEventualidad;
import fox.hound.spring.models.puente.OrdenServicioEventualidad;
import fox.hound.spring.models.puente.SolicitudEventualidad;

@Entity
@Table(name="eventualidad")
@CustomJsonRootName(plural = "eventualidad", singular = "eventualidad")
public class Eventualidad extends Maestro {
	
	@OneToMany(mappedBy="eventualidad")
	@JsonManagedReference(value="eventualidad-ordenServicioEventualidads")
	private List<OrdenServicioEventualidad> ordenServicioEventualidads;
	
	@ManyToOne
	@JoinColumn(name="tipoEventualidad_id")
	@JsonBackReference(value="eventualidad-tipoEventualidad")
	private TipoEventualidad tipoEventualidad;
	
	@OneToMany(mappedBy="eventualidad")
	@JsonManagedReference(value="eventualidad-solicitudEventualidads")
	private List<SolicitudEventualidad> solicitudEventualidads;
	
	public Eventualidad(Long id, String name, int estatusId, String tipoEventualidad) {
		super(id, name, estatusId);
		this.tipoEventualidad = new TipoEventualidad(tipoEventualidad);
	}
	public Eventualidad(String id) {
		super(id);
	}
	public Eventualidad() {
		super();
	}
	public List<OrdenServicioEventualidad> getOrdenServicioEventualidads() {
		return ordenServicioEventualidads;
	}
	public void setOrdenServicioEventualidads(List<OrdenServicioEventualidad> ordenServicioEventualidads) {
		this.ordenServicioEventualidads = ordenServicioEventualidads;
	}
	
	
}