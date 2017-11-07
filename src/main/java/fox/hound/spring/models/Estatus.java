package fox.hound.spring.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import fox.hound.spring.beans.CustomJsonRootName;

//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "estatus", singular = "estatus")
@Entity
public class Estatus {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
    private String descripcion;
	public Estatus(Long id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}
	public Estatus(String id) {
		this.id = Long.valueOf(id);
	}
	public Estatus() {
		
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

}
