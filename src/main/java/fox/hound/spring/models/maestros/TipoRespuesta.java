package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.Respuesta;

@Entity
@Table(name="tipo_respuesta")
@CustomJsonRootName(plural = "tipoRespuesta", singular = "tipoRespuesta")
public class TipoRespuesta extends Maestro {

	@OneToMany(mappedBy="tipoRespuesta")
	@JsonManagedReference(value="respuesta-tipoRespuesta")
	private List<Respuesta> respuestas;
	
	public TipoRespuesta(Long id, String name, int estatusId) {
		super(id, name, estatusId);
	}
	public TipoRespuesta(String id) {
		super(id);
	}
	public TipoRespuesta() {
		
	}
	public List<Respuesta> getRespuestas() {
		return respuestas;
	}
	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

}
