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
import fox.hound.spring.models.Servicio;
import fox.hound.spring.models.maestros.Maestro;
import fox.hound.spring.models.maestros.Tarea;
import fox.hound.spring.models.puente.DetalleOrdenServicio;

@Entity
@Table(name="servicio_tarea")
@CustomJsonRootName(plural = "servicioTarea", singular = "servicioTarea")
public class ServicioTarea extends Maestro {
	
	@ManyToOne
	@JoinColumn(name="tarea_id")
	@JsonBackReference(value="servicioTarea-tarea")
	private Tarea tarea;
	
	@ManyToOne
	@JoinColumn(name="servicio_id")
	@JsonBackReference(value="servicio-servicioTareas")
	private Servicio servicio;
	
	@OneToMany(mappedBy="servicioTarea")
	@JsonManagedReference(value="servicioTarea-detalleOrdenServicios")
	private List<DetalleOrdenServicio> detalleOrdenServicios;

	public ServicioTarea(Long id, String name, String estatusId, String estadoId, String tareaId, String servicioId) {
		super(id, name, estatusId);
		this.tarea = new Tarea(tareaId);
		this.servicio = new Servicio(servicioId);
	}
	public ServicioTarea(String id) {
		super(id);
	}
	public ServicioTarea() {
		super();
	}
	public Tarea getTarea() {
		return tarea;
	}
	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
}