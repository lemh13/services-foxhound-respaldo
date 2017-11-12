package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Persona;

@Entity
@Table(name="tipo_persona")
@CustomJsonRootName(plural = "tipoPersona", singular = "tipoPersona")
public class TipoPersona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(nullable = false)
    private String descripcion;
	
	@OneToMany(mappedBy="tipoPersona")
	@JsonManagedReference(value="personas-tipoPersona")
	private List<Persona> personas;

	
	public TipoPersona(Long id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}
	public TipoPersona(String id) {
		this.id = Long.valueOf(id);
	}
	public TipoPersona() {
		
	}
	public List<Persona> getPersonas() {
		return personas;
	}
	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
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
