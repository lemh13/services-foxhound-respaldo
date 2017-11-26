package fox.hound.spring.models.combo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Persona;
import fox.hound.spring.models.maestros.Maestro;

@Entity
@Table(name="buzon_sugerencia")
@CustomJsonRootName(plural = "buzonSugerencia", singular = "buzonSugerencia")
public class BuzonSugerencia extends Maestro {

	@Column(nullable = false)
    private String asunto;
	
	@ManyToOne
	@JoinColumn(name="asuntoComentario_id")
	@JsonBackReference(value="asuntoComentario-buzonSugerencias")
	private AsuntoComentario asuntoComentario;
	
	@ManyToOne
	@JoinColumn(name="persona_id")
	@JsonBackReference(value="persona-buzonSugerencia")
	private Persona persona;
	
	public BuzonSugerencia() {
		super();
	}
	public BuzonSugerencia(Long id, String descripcion, int estatusId, String asunto,
			String asuntoComentarioId, String personaId) {
		super(id, descripcion, estatusId);
		this.asunto = asunto;
		this.asuntoComentario = new AsuntoComentario(asuntoComentarioId);
		this.persona = new Persona(personaId);
	}
	public BuzonSugerencia(String id) {
		super(id);
	}
	public AsuntoComentario getAsuntoComentario() {
		return asuntoComentario;
	}
	public void setAsuntoComentario(AsuntoComentario asuntoComentario) {
		this.asuntoComentario = asuntoComentario;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	
	
}
