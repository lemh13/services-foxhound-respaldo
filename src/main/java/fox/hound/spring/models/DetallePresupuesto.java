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
import fox.hound.spring.models.puente.DetalleDiagnosticoVisita;
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
	@JoinColumn(name="detalleDiagnosticoVisita_id")
	@JsonBackReference(value="detallePresupuestos-detalleDiagnosticoVisita")
	private DetalleDiagnosticoVisita detalleDiagnosticoVisita;
	
	@ManyToOne
	@JoinColumn(name="presupuesto_id")
	@JsonBackReference(value="detallePresupuesto-presupuesto")
	private Presupuesto presupuesto;

	@ManyToOne
	@JoinColumn(name="solicitudServicio_id")
	@JsonBackReference(value="detallePresupuesto-solicitudServicio")
	private SolicitudServicio solicitudServicio;
	
	public DetallePresupuesto(Long id, double costo, double area, int estatusId, String detalleDiagnosticoVisita,
			String presupuesto, String solicitudServicio) {
		super(estatusId);
		this.id = id;
		this.costo = costo;
		this.detalleDiagnosticoVisita = new DetalleDiagnosticoVisita(detalleDiagnosticoVisita);
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
	
	public Presupuesto getPresupuesto() {
		return presupuesto;
	}
	public DetalleDiagnosticoVisita getDetalleDiagnosticoVisita() {
		return detalleDiagnosticoVisita;
	}
	public void setDetalleDiagnosticoVisita(DetalleDiagnosticoVisita detalleDiagnosticoVisita) {
		this.detalleDiagnosticoVisita = detalleDiagnosticoVisita;
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
