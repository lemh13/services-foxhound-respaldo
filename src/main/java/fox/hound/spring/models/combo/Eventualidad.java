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
	@Transient
	private Long padre_id;
	@Transient
	private String padre_descripcion;
	
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
	public void setTipoEventualidad(TipoEventualidad tipoEventualidad) {
		this.tipoEventualidad = tipoEventualidad;
	}
	public TipoEventualidad getTipoEventualidad() {
		return tipoEventualidad;
	}
	public Long getPadre_id() {
		return tipoEventualidad.getId();
	}
	public void setPadre_id(Long padre_id) {
		this.padre_id = padre_id;
	}
	public String getPadre_descripcion() {
		return tipoEventualidad.getDescripcion();
	}
	public void setPadre_descripcion(String padre_descripcion) {
		this.padre_descripcion = padre_descripcion;
	}
	public List<SolicitudEventualidad> getSolicitudEventualidads() {
		return solicitudEventualidads;
	}
	public void setSolicitudEventualidads(List<SolicitudEventualidad> solicitudEventualidads) {
		this.solicitudEventualidads = solicitudEventualidads;
	}
	
	
	
}