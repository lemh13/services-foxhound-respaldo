package fox.hound.spring.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.puente.RolFuncion;

@Entity
//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "menu", singular = "menu")
public class Menu {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
    private String descripcion;
	@Column(nullable = false)
    private String icono;
	@Column(nullable = false)
    private String url_path;
	@Column(nullable = false)
    private Long padre;
	
	@OneToMany(mappedBy="menu")
	@JsonManagedReference(value="menu-rolFuncion")
	private List<RolFuncion> rolFuncion;
	
	public Menu(Long id, String descripcion, String icono, String url_path, Long padre) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.icono = icono;
		this.url_path = url_path;
		this.padre = padre;
	}
	public Menu(String id) {
		this.id = Long.valueOf(id);
	}
	public Menu() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public Long getPadre() {
		return padre;
	}
	public void setPadre(Long padre) {
		this.padre = padre;
	}
	public List<RolFuncion> getRolFuncion() {
		return rolFuncion;
	}
	public void setRolFuncion(List<RolFuncion> rolFuncion) {
		this.rolFuncion = rolFuncion;
	}
	
}
