package fox.hound.spring.models.combo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Persona;
import fox.hound.spring.models.maestros.Maestro;

@Entity
@Table(name="rol")
@CustomJsonRootName(plural = "rol", singular = "rol")
public class Rol extends Maestro {
	
	@OneToMany(mappedBy="rol")
	@JsonManagedReference(value="persona-rol")
	private List<Persona> personas;
	
//	@OneToMany(mappedBy="rol")
//	@JsonManagedReference(value="rolFuncions-rol")
//	private List<RolFuncion> rolFuncions;
	
	private String menu;

	public Rol(Long id, String name, int estatusId, String estadoId, String menu) {
		super(id, name, estatusId);
	}
	public Rol(String id) {
		super(id);
	}
	public Rol() {
		super();
	}
	public List<Persona> getPersonas() {
		return personas;
	}
	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	
	
//	public List<RolFuncion> getRolFuncions() {
//		return rolFuncions;
//	}
//	public void setRolFuncions(List<RolFuncion> rolFuncions) {
//		this.rolFuncions = rolFuncions;
//	}
	
}