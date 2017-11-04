package fox.hound.spring.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;

// http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "inmuebles", singular = "inmueble")
@Entity
public class Inmueble {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String direccion;
	@ManyToOne
	@JoinColumn(name="usuarioId", nullable = false)
	@JsonBackReference
	private Usuario usuario;
	
	public Inmueble(Long id, String direccion, String usuarioId) {
		super();
		this.id = id;
		this.direccion = direccion;
		this.usuario = new Usuario(usuarioId);
	}
	public Inmueble() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
