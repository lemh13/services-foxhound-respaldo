package fox.hound.spring.models.maestros;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import fox.hound.spring.models.Base;

@MappedSuperclass
public class Maestro extends Base {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(nullable = false)
    private String descripcion;
	
	public Maestro(Long id, String descripcion, int estatusId) {
		super(estatusId);
		this.id = id;
		this.descripcion = descripcion;
	}
	public Maestro(String id) {
		this.id = Long.valueOf(id);
	}
	public Maestro() {
		super();
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
