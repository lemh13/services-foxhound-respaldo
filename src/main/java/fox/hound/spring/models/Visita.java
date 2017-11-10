package fox.hound.spring.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.DiagnosticoVisita;
import fox.hound.spring.models.combo.Reclamo;
import fox.hound.spring.models.maestros.EstadoServicio;
import fox.hound.spring.models.maestros.TipoVisita;
import fox.hound.spring.models.maestros.Turno;
import fox.hound.spring.models.puente.Solicitud;
import fox.hound.spring.models.puente.TrabajadorVisita;

@Entity
@Table(name="visita")
//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "visita", singular = "visita")
public class Visita extends Base {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Date fechaVisita;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Reclamo reclamo;
	
	@ManyToOne
	@JoinColumn(name="turno_id")
	@JsonBackReference(value="visita-turno")
	private Turno turno;
	
	@ManyToOne
	@JoinColumn(name="tipoVisita_id")
	@JsonBackReference(value="visita-tipoVisita")
	private TipoVisita tipoVisita;
	
	@OneToMany(mappedBy="visita")
	@JsonManagedReference(value="visita-trabajadorVisitas")
	private List<TrabajadorVisita> trabajadorVisitas;
	
	@ManyToOne
	@JoinColumn(name="solicitud_id")
	@JsonBackReference(value="visita-solicitud")
	private Solicitud solicitud;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private DiagnosticoVisita diagnosticoVisita;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private EstadoServicio estadoServicio;
	
	public Visita(Long id, Date fechaVisita, String estatus,
			String tipoVisita, String turno, String solicitud) {
		super(estatus);
		this.id = id;
		this.fechaVisita = fechaVisita;
		this.turno = new Turno(turno);
		this.tipoVisita = new TipoVisita(tipoVisita);
		this.solicitud = new Solicitud(solicitud);
	}
	public Reclamo getReclamo() {
		return reclamo;
	}
	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
	}
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	public TipoVisita getTipoVisita() {
		return tipoVisita;
	}
	public void setTipoVisita(TipoVisita tipoVisita) {
		this.tipoVisita = tipoVisita;
	}
	public Visita(String id) {
		this.id = Long.valueOf(id);
	}
	public Visita() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaVisita() {
		return fechaVisita;
	}
	public void setFechaVisita(Date fechaVisita) {
		this.fechaVisita = fechaVisita;
	}
	
	
}
