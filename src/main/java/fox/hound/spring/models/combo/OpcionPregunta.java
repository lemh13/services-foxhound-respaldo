package fox.hound.spring.models.combo;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.maestros.Maestro;
import fox.hound.spring.models.maestros.Opcion;
import fox.hound.spring.models.maestros.Pregunta;

@Entity
@Table(name="opcion_pregunta")
//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "opcionPregunta", singular = "opcionPregunta")
public class OpcionPregunta extends Maestro {
	
	@ManyToOne
	@JoinColumn(name="opcion_id")
	@JsonBackReference(value="opcionPregunta-opcion")
	private Opcion opcion;
	
	@ManyToOne
	@JoinColumn(name="pregunta_id")
	@JsonBackReference(value="opcionPregunta-pregunta")
	private Pregunta pregunta;
	
	public OpcionPregunta(Long id, String name, String estatusId, String opcionId, String preguntaId) {
		super(id, name, estatusId);
		this.opcion = new Opcion(opcionId);
		this.pregunta = new Pregunta(preguntaId);
	}
	public OpcionPregunta(String id) {
		super(id);
	}
	public OpcionPregunta() {
		super();
	}
	public Opcion getOpcion() {
		return opcion;
	}
	public void setOpcion(Opcion opcion) {
		this.opcion = opcion;
	}
	public Pregunta getPregunta() {
		return pregunta;
	}
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

}
