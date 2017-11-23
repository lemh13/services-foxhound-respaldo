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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
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
	@Transient
	private Long tecnico_id;
	@Transient
	private String tecnico_nombre;
	@Transient
	private String tecnico_tlf;
	
	@ManyToOne
	@JoinColumn(name="solicitud_id")
	@JsonBackReference(value="visita-solicitud")
	private Solicitud solicitud;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private DiagnosticoVisita diagnosticoVisita;
	
	@ManyToOne
	@JoinColumn(name="ordenServicio_id")
	@JsonBackReference(value="ordenServicio-visitas")
	private OrdenServicio ordenServicio;
	
	public Visita(Long id, Date fechaVisita, int estatus,
			String tipoVisita, String turno, String solicitud, String ordenServicio) {
		super(estatus);
		this.id = id;
		this.fechaVisita = fechaVisita;
		this.turno = new Turno(turno);
		this.tipoVisita = new TipoVisita(tipoVisita);
		this.solicitud = new Solicitud(solicitud);
		this.ordenServicio = new OrdenServicio(ordenServicio);
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
	public List<TrabajadorVisita> getTrabajadorVisitas() {
		return trabajadorVisitas;
	}
	public void setTrabajadorVisitas(List<TrabajadorVisita> trabajadorVisitas) {
		this.trabajadorVisitas = trabajadorVisitas;
	}
	public Solicitud getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}
	public DiagnosticoVisita getDiagnosticoVisita() {
		return diagnosticoVisita;
	}
	public void setDiagnosticoVisita(DiagnosticoVisita diagnosticoVisita) {
		this.diagnosticoVisita = diagnosticoVisita;
	}
	public OrdenServicio getOrdenServicio() {
		return ordenServicio;
	}
	public void setOrdenServicio(OrdenServicio ordenServicio) {
		this.ordenServicio = ordenServicio;
	}
	public Long getTecnico_id() {
		return trabajadorVisitas.get(0).getTrabajador().getId();
	}
	public void setTecnico_id(Long tecnico_id) {
		this.tecnico_id = tecnico_id;
	}
	public String getTecnico_nombre() {
		return trabajadorVisitas.get(0).getTrabajador().getNombre();
	}
	public void setTecnico_nombre(String tecnico_nombre) {
		this.tecnico_nombre = tecnico_nombre;
	}
	public String getTecnico_tlf() {
		return trabajadorVisitas.get(0).getTrabajador().getTelefono();
	}
	public void setTecnico_tlf(String tecnico_tlf) {
		this.tecnico_tlf = tecnico_tlf;
	}
	
	
}
