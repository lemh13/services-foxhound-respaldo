package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.OpcionPregunta;

@Entity
@Table(name="pregunta")
@CustomJsonRootName(plural = "pregunta", singular = "pregunta")
public class Pregunta extends Maestro {
	
	@OneToMany(mappedBy="pregunta")
	@JsonManagedReference(value="opcionPregunta-pregunta")
	private List<OpcionPregunta> opcionPreguntas;
	
	public Pregunta(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	public Pregunta(String id) {
		super(id);
	}
	public Pregunta() {
		super();
	}
	public List<OpcionPregunta> getOpcionPreguntas() {
		return opcionPreguntas;
	}
	public void setOpcionPreguntas(List<OpcionPregunta> opcionPreguntas) {
		this.opcionPreguntas = opcionPreguntas;
	}
	
}
