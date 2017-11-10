package fox.hound.spring.models.combo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.maestros.Maestro;

@Entity
@Table(name="comentario_externo")
@CustomJsonRootName(plural = "comentarioExterno", singular = "comentarioExterno")
public class ComentarioExterno extends Maestro {

	@Column(nullable = false)
	private String correoEmisor;
	
	@ManyToOne
	@JoinColumn(name="asuntoComentario_id")
	@JsonBackReference(value="asuntoComentario-comentarioExternos")
	private AsuntoComentario asuntoComentario;
	
	public ComentarioExterno() {
		super();
	}
	public ComentarioExterno(Long id, String descripcion, String estatusId, String correoEmisor,
			String asuntoComentarioId) {
		super(id, descripcion, estatusId);
		this.correoEmisor = correoEmisor;
		this.asuntoComentario = new AsuntoComentario(asuntoComentarioId);
	}
	public ComentarioExterno(String id) {
		super(id);
	}
	public String getCorreoEmisor() {
		return correoEmisor;
	}
	public void setCorreoEmisor(String correoEmisor) {
		this.correoEmisor = correoEmisor;
	}
	public AsuntoComentario getAsuntoComentario() {
		return asuntoComentario;
	}
	public void setAsuntoComentario(AsuntoComentario asuntoComentario) {
		this.asuntoComentario = asuntoComentario;
	}
	
}
