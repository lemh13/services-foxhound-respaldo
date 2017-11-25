package fox.hound.spring.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.maestros.CalificarServicio;
import fox.hound.spring.models.maestros.Maestro;
import fox.hound.spring.models.puente.ReclamoOrdenEntrega;
import fox.hound.spring.models.puente.ValoracionOrdenServicio;

@Entity
@Table(name="orden_entrega")
@CustomJsonRootName(plural = "ordenEntrega", singular = "ordenEntrega")
public class OrdenEntrega extends Maestro {

	
	@OneToMany(mappedBy="ordenEntrega")
	@JsonManagedReference(value="ordenEntrega-calificarServicio")
	private List<CalificarServicio> calificarServicios;
	
	@OneToMany(mappedBy="ordenEntrega")
	@JsonManagedReference(value="ordenEntrega-reclamoOrdenEntrega")
	private List<ReclamoOrdenEntrega> reclamoordenentrega;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "ordenEntrega", cascade = CascadeType.ALL)
	private ValoracionOrdenServicio valoracionOrdenServicio;
	
	public OrdenEntrega() {
		super();
	}
	public List<ReclamoOrdenEntrega> getReclamoordenentrega() {
		return reclamoordenentrega;
	}
	public void setReclamoordenentrega(List<ReclamoOrdenEntrega> reclamoordenentrega) {
		this.reclamoordenentrega = reclamoordenentrega;
	}
	public OrdenEntrega(Long id, String descripcion, int estatusId) {
		super(id, descripcion, estatusId);
	}
	public OrdenEntrega(String id) {
		super(id);
	}
	
	public ValoracionOrdenServicio getValoracionOrdenServicio() {
		return valoracionOrdenServicio;
	}
	public void setValoracionOrdenServicio(ValoracionOrdenServicio valoracionOrdenServicio) {
		this.valoracionOrdenServicio = valoracionOrdenServicio;
	}
	public List<CalificarServicio> getCalificarServicios() {
		return calificarServicios;
	}
	public void setCalificarServicios(List<CalificarServicio> calificarServicios) {
		this.calificarServicios = calificarServicios;
	}
	
	
}
