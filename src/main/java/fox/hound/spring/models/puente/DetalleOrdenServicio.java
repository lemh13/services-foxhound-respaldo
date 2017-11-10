package fox.hound.spring.models.puente;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.OrdenServicio;
import fox.hound.spring.models.Trabajador;
import fox.hound.spring.models.combo.ServicioHerramienta;
import fox.hound.spring.models.combo.ServicioTarea;

@Entity
@Table(name="detalle_orden_servicio")
@CustomJsonRootName(plural = "detalleOrdenServicio", singular = "detalleOrdenServicio")
public class DetalleOrdenServicio extends Puente {

	@OneToMany(mappedBy="detalleOrdenServicio")
	@JsonManagedReference(value="trabajador-detalleOrdenServicio")
	private List<Trabajador> trabajadors;
	
	@ManyToOne
	@JoinColumn(name="servicioHerramienta_id")
	@JsonBackReference(value="servicioHerramienta-detalleOrdenServicios")
	private ServicioHerramienta servicioHerramienta;
	
	@ManyToOne
	@JoinColumn(name="servicioTarea_id")
	@JsonBackReference(value="servicioTarea-detalleOrdenServicios")
	private ServicioTarea servicioTarea;
	
	@ManyToOne
	@JoinColumn(name="ordenServicio_id")
	@JsonBackReference(value="ordenServicio-detalleOrdenServicios")
	private OrdenServicio ordenServicio;
	
	@ManyToOne
	@JoinColumn(name="solicitudServicio_id")
	@JsonBackReference(value="solicitudServicio-detalleOrdenServicios")
	private SolicitudServicio solicitudServicio;
	
	public DetalleOrdenServicio() {
		super();
	}
	public DetalleOrdenServicio(Long id, String estatusId, String servicioHerramienta,
			String servicioTarea, String ordenServicio, String solicitudServicio) {
		super(id, estatusId);
		this.servicioHerramienta = new ServicioHerramienta(servicioHerramienta);
		this.servicioTarea = new ServicioTarea(servicioTarea);
		this.ordenServicio = new OrdenServicio(ordenServicio);
		this.solicitudServicio = new SolicitudServicio(solicitudServicio);
	}
	public DetalleOrdenServicio(String id) {
		super(id);
	}
	public List<Trabajador> getTrabajadors() {
		return trabajadors;
	}
	public void setTrabajadors(List<Trabajador> trabajadors) {
		this.trabajadors = trabajadors;
	}
	public ServicioHerramienta getServicioHerramienta() {
		return servicioHerramienta;
	}
	public void setServicioHerramienta(ServicioHerramienta servicioHerramienta) {
		this.servicioHerramienta = servicioHerramienta;
	}
	public ServicioTarea getServicioTarea() {
		return servicioTarea;
	}
	public void setServicioTarea(ServicioTarea servicioTarea) {
		this.servicioTarea = servicioTarea;
	}
	public OrdenServicio getOrdenServicio() {
		return ordenServicio;
	}
	public void setOrdenServicio(OrdenServicio ordenServicio) {
		this.ordenServicio = ordenServicio;
	}
	public SolicitudServicio getSolicitudServicio() {
		return solicitudServicio;
	}
	public void setSolicitudServicio(SolicitudServicio solicitudServicio) {
		this.solicitudServicio = solicitudServicio;
	}
	
}
