package fox.hound.spring.models.combo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.maestros.Maestro;

@Entity
@Table(name="asunto_comentario")
@CustomJsonRootName(plural = "asuntoComentario", singular = "asuntoComentario")
public class AsuntoComentario extends Maestro {

	@OneToMany(mappedBy="asuntoComentario")
	@JsonManagedReference(value="asuntoComentario-comentarioExternos")
	private List<ComentarioExterno> comentarioExternos;
	
	@OneToMany(mappedBy="asuntoComentario")
	@JsonManagedReference(value="asuntoComentario-buzonSugerencias")
	private List<BuzonSugerencia> buzonSugerencias;
	
	public AsuntoComentario() {
		super();
	}
	public AsuntoComentario(Long id, String descripcion, String estatusId) {
		super(id, descripcion, estatusId);
	}
	public AsuntoComentario(String id) {
		super(id);
	}
	public List<ComentarioExterno> getComentarioExternos() {
		return comentarioExternos;
	}
	public void setComentarioExternos(List<ComentarioExterno> comentarioExternos) {
		this.comentarioExternos = comentarioExternos;
	}
	public List<BuzonSugerencia> getBuzonSugerencias() {
		return buzonSugerencias;
	}
	public void setBuzonSugerencias(List<BuzonSugerencia> buzonSugerencias) {
		this.buzonSugerencias = buzonSugerencias;
	}
	
}
