package fox.hound.spring.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.maestros.Cargo;
import fox.hound.spring.models.puente.DetalleOrdenServicio;
import fox.hound.spring.models.puente.TrabajadorVisita;

@Entity
@Table(name="trabajador")
//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "trabajadores", singular = "trabajador")
public class Trabajador extends Persona {
	
	@Column(nullable = false)
	private Date fecha_ingreso;
	@Column(nullable = false)
	private boolean disponibilidad;
	
	@ManyToOne
	@JoinColumn(name="cargo_id")
	@JsonBackReference(value="trabajador-cargo")
	private Cargo cargo;

	@OneToMany(mappedBy="trabajador")
	@JsonManagedReference(value="trabajador-TrabajadorVisitas")
	private List<TrabajadorVisita> TrabajadorVisitas;
	
	@ManyToOne
	@JoinColumn(name="DetalleOrdenServicio_id")
	@JsonBackReference(value="trabajador-detalleOrdenServicio")
	private DetalleOrdenServicio detalleOrdenServicio;
	
	@ManyToOne
	@JoinColumn(name="empresa_id")
	@JsonBackReference(value="empresa-trabajadores")
	private Empresa empresa;
	
	public Trabajador(Long id, String nombre, char sexo, String direccion, int identificacion, Long estatus,
			Date fecha_de_nacimiento, String telefono, String estatusId, String tipoPersonaId, String sectorId,
			String email, String password, String rolId, Date fecha_ingreso, boolean disponibilidad, String cargoId,
			String empresaId, String detalleOrdenServicio) {
		super(id, nombre, sexo, direccion, identificacion, estatus, fecha_de_nacimiento, telefono, estatusId, tipoPersonaId,
				sectorId, email, password, rolId);
		this.fecha_ingreso = fecha_ingreso;
		this.disponibilidad = disponibilidad;
		this.cargo = new Cargo(cargoId);
		this.empresa = new Empresa(empresaId);
		this.detalleOrdenServicio = new DetalleOrdenServicio(detalleOrdenServicio);
	}
	public Trabajador(String id) {
		super(id);
	}
	public Trabajador() {
		super();
	}
	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}
	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}
	public boolean isDisponibilidad() {
		return disponibilidad;
	}
	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public List<TrabajadorVisita> getTrabajadorVisitas() {
		return TrabajadorVisitas;
	}
	public void setTrabajadorVisitas(List<TrabajadorVisita> trabajadorVisitas) {
		TrabajadorVisitas = trabajadorVisitas;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}
