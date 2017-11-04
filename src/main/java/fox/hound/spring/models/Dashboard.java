package fox.hound.spring.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import fox.hound.spring.beans.CustomJsonRootName;

//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "dashboard", singular = "dashboard")
@Entity
public class Dashboard {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String url_path;
	@Column(nullable = false)
	private Long padre_id;
	public Dashboard(Long id, String nombre, String url_path, Long padre_id) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.url_path = url_path;
		this.padre_id = padre_id;
	}
	public Dashboard() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUrl_path() {
		return url_path;
	}
	public void setUrl_path(String url_path) {
		this.url_path = url_path;
	}
	public Long getPadre_id() {
		return padre_id;
	}
	public void setPadre_id(Long padre_id) {
		this.padre_id = padre_id;
	}
	
}
