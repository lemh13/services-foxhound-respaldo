package fox.hound.spring.print;

import java.util.Date;
import java.util.List;

import fox.hound.spring.models.Persona;

public class RolPrint {
	
	private Long id;
	private List<Persona> personas;
	private List<Integer> menu;
	private String descripcion;
	private Date fecha_creacion;
	private Date fecha_modificacion;
	private int estatus;
	public RolPrint(Long id, List<Persona> personas, List<Integer> menu, String descripcion, Date fecha_creacion,
			Date fecha_modificacion, int estatus) {
		super();
		this.id = id;
		this.personas = personas;
		this.menu = menu;
		this.descripcion = descripcion;
		this.fecha_creacion = fecha_creacion;
		this.fecha_modificacion = fecha_modificacion;
		this.estatus = estatus;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Persona> getPersonas() {
		return personas;
	}
	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
	public List<Integer> getMenu() {
		return menu;
	}
	public void setMenu(List<Integer> menu) {
		this.menu = menu;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public Date getFecha_modificacion() {
		return fecha_modificacion;
	}
	public void setFecha_modificacion(Date fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}
	public int getEstatus() {
		return estatus;
	}
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

}
