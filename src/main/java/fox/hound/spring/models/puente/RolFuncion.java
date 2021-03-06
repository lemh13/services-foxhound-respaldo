package fox.hound.spring.models.puente;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.Rol;

@Entity
@Table(name="rol_funcion")
@CustomJsonRootName(plural = "rolFuncion", singular = "rolFuncion")
public class RolFuncion extends Puente {

//	@ManyToOne
//	@JoinColumn(name="menu_id")
//	@JsonBackReference(value="menu-rolFuncion")
//	private Menu menu;

	@ManyToOne
	@JoinColumn(name="rol_id")
	@JsonBackReference(value="rolFuncions-rol")
	private Rol rol;
	
	private int menu;
	
	public RolFuncion(Long id, int estatusId, String rolId, int menu) {
		super(id, estatusId);
//		this.menu = new Menu(menuId);
		this.rol = new Rol(rolId);
		this.menu = menu;
	}
	public RolFuncion(String id) {
		super(id);
	}
	public RolFuncion() {
		super();
	}
//	public Menu getMenu() {
//		return menu;
//	}
//	public void setMenu(Menu menu) {
//		this.menu = menu;
//	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public int getMenu() {
		return menu;
	}
	public void setMenu(int menu) {
		this.menu = menu;
	}
	
}
