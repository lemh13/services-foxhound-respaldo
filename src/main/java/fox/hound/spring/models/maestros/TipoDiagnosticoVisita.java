package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="tipo_diagnostico_visita")
@CustomJsonRootName(plural = "tipoDiagnosticoVisita", singular = "tipoDiagnosticoVisita")
public class TipoDiagnosticoVisita extends Maestro {

	public TipoDiagnosticoVisita(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	public TipoDiagnosticoVisita(String id) {
		super(id);
	}
	public TipoDiagnosticoVisita() {
		
	}

}
