package fox.hound.spring.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.Presupuesto;
import fox.hound.spring.models.puente.DetalleDiagnostico;
import fox.hound.spring.models.puente.SolicitudServicio;

@Entity
@Table(name="detalle_presupuesto")
@CustomJsonRootName(plural = "detallePresupuesto", singular = "detallePresupuesto")
public class DetallePresupuesto extends Base {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
    private double area;
	@Column(nullable = false)
    private double costo;
	
	@ManyToOne
	@JoinColumn(name="detalleDiagnostico_id")
	@JsonBackReference(value="detallePresupuesto-detalleDiagnostico")
	private DetalleDiagnostico detalleDiagnostico;
	
	@ManyToOne
	@JoinColumn(name="presupuesto_id")
	@JsonBackReference(value="detallePresupuesto-presupuesto")
	private Presupuesto presupuesto;

	@ManyToOne
	@JoinColumn(name="solicitudServicio_id")
	@JsonBackReference(value="detallePresupuesto-solicitudServicio")
	private SolicitudServicio solicitudServicio;
	
	public DetallePresupuesto(Long id, double costo, double area, String estatusId, String detalleDiagnosticoId,
			String presupuesto, String solicitudServicio) {
		super(estatusId);
		this.id = id;
		this.costo = costo;
		this.detalleDiagnostico = new DetalleDiagnostico(detalleDiagnosticoId);
		this.presupuesto = new Presupuesto(presupuesto);
		this.solicitudServicio = new SolicitudServicio(solicitudServicio);
	}
	public DetallePresupuesto(String id) {
		this.id = Long.valueOf(id);
	}
	public DetallePresupuesto() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	public DetalleDiagnostico getDetalleDiagnostico() {
		return detalleDiagnostico;
	}
	public void setDetalleDiagnostico(DetalleDiagnostico detalleDiagnostico) {
		this.detalleDiagnostico = detalleDiagnostico;
	}
	public Presupuesto getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}
	public SolicitudServicio getSolicitudServicio() {
		return solicitudServicio;
	}
	public void setSolicitudServicio(SolicitudServicio solicitudServicio) {
		this.solicitudServicio = solicitudServicio;
	}
	
	
}
