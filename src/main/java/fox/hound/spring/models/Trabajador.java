package fox.hound.spring.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import fox.hound.spring.beans.CustomJsonRootName;

//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "trabajadores", singular = "trabajador")
@Entity
public class Trabajador  {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//ManyToOne
	//Cargo
	//OneToMany
	//HorarioTrabajador
	//OneToMany
	//TrabajadorTipoServicio
	//OneToMany
	//TrabajadorVisita
	//ManyToOne
	//DetalleOrdenServicio
	//ManyToOne
	//Empresa
	@Column(nullable = false)
	private Date fecha_ingreso;
	@Column(nullable = false)
	private boolean disponibilidad;
	
	
	public Trabajador() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

}
