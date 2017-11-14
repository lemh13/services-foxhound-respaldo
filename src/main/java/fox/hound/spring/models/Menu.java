package fox.hound.spring.models;

import java.util.List;

import fox.hound.spring.beans.CustomJsonRootName;

@CustomJsonRootName(plural = "menu", singular = "menu")
public class Menu {
	
	private int id;
    private String descripcion;
    private String icono;
    private String url_path;
    private List<Menu> child;
	
	public Menu(int id, String descripcion, String icono, String url_path, List<Menu> child) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.icono = icono;
		this.url_path = url_path;
		this.child = child;
	}
	public Menu(String id) {
		this.id = Integer.valueOf(id);
	}
	public Menu() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getIcono() {
		return icono;
	}
	public void setIcono(String icono) {
		this.icono = icono;
	}
	public String getUrl_path() {
		return url_path;
	}
	public void setUrl_path(String url_path) {
		this.url_path = url_path;
	}
	public List<Menu> getChild() {
		return child;
	}
	public void setChild(List<Menu> child) {
		this.child = child;
	}
	
	
}
