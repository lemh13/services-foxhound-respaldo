package fox.hound.spring.models.puente;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.OrdenEntrega;
import fox.hound.spring.models.combo.Respuesta;

@Entity
@Table(name="valoracion_orden_servicio")
@CustomJsonRootName(plural = "valoracionOrdenServicio", singular = "valoracionOrdenServicio")
public class ValoracionOrdenServicio extends Puente {

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private OrdenEntrega ordenEntrega;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "valoracionOrdenServicio", cascade = CascadeType.ALL)
	private Respuesta respuesta;
	
	public ValoracionOrdenServicio() {
		super();
	}
	public ValoracionOrdenServicio(Long id, String estatusId) {
		super(id, estatusId);
	}
	public ValoracionOrdenServicio(String id) {
		super(id);
	}
	public OrdenEntrega getOrdenEntrega() {
		return ordenEntrega;
	}
	public void setOrdenEntrega(OrdenEntrega ordenEntrega) {
		this.ordenEntrega = ordenEntrega;
	}
	public Respuesta getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}

}
