package fox.hound.spring.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.maestros.Maestro;

@Entity
@Table(name="noticia")
@CustomJsonRootName(plural = "noticia", singular = "noticia")
public class Noticia extends Maestro {

	@Column(nullable = false)
	private String titulo;
	
	@ManyToOne
	@JoinColumn(name="empresa_id")
	@JsonBackReference(value="empresa-noticias")
	private Empresa empresa;
	
	public Noticia() {
		super();
	}
	public Noticia(Long id, String descripcion, String estatusId, String titulo, String empresaId) {
		super(id, descripcion, estatusId);
		this.titulo = titulo;
		this.empresa = new Empresa(empresaId);
	}
	public Noticia(String id) {
		super(id);
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
