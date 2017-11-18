package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.Motivo;

@Entity
@Table(name="tipo_motivo")
@CustomJsonRootName(plural = "tipoMotivo", singular = "tipoMotivo")
public class TipoMotivo extends Maestro {
	
	@OneToMany(mappedBy="tipoMotivo")
	@JsonManagedReference(value="motivo-tipoMotivo")
	private List<Motivo> motivos;

	public TipoMotivo(Long id, String name, int estatusId) {
		super(id, name, estatusId);
	}
	public TipoMotivo(String id) {
		super(id);
	}
	public TipoMotivo() {
		
	}
	public List<Motivo> getMotivos() {
		return motivos;
	}
	public void setMotivos(List<Motivo> motivos) {
		this.motivos = motivos;
	}

}
