package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.DiagnosticoVisita;

@Entity
@Table(name="tipo_diagnostico_visita")
@CustomJsonRootName(plural = "tipoDiagnosticoVisita", singular = "tipoDiagnosticoVisita")
public class TipoDiagnosticoVisita extends Maestro {
	
	@OneToMany(mappedBy="tipoDiagnosticoVisita")
	@JsonManagedReference(value="diagnosticoVisita-tipoDiagnosticoVisita")
	private List<DiagnosticoVisita> diagnosticoVisitas;

	public TipoDiagnosticoVisita(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	public TipoDiagnosticoVisita(String id) {
		super(id);
	}
	public TipoDiagnosticoVisita() {
		
	}
	public List<DiagnosticoVisita> getDiagnosticoVisitas() {
		return diagnosticoVisitas;
	}
	public void setDiagnosticoVisitas(List<DiagnosticoVisita> diagnosticoVisitas) {
		this.diagnosticoVisitas = diagnosticoVisitas;
	}

}
