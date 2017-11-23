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

import com.fasterxml.jackson.annotation.JsonBackReference;
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

	@ManyToOne
	@JoinColumn(name="reclamoOrdenEntrega_id")
	@JsonBackReference(value="ordenEntrega-reclamoOrdenEntrega")
	private ReclamoOrdenEntrega reclamoOrdenEntrega;
	
	@OneToMany(mappedBy="ordenEntrega")
	@JsonManagedReference(value="ordenEntrega-calificarServicio")
	private List<CalificarServicio> calificarServicios;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "ordenEntrega", cascade = CascadeType.ALL)
	private ValoracionOrdenServicio valoracionOrdenServicio;
	
	public OrdenEntrega() {
		super();
	}
	public OrdenEntrega(Long id, String descripcion, int estatusId, String reclamoOrdenEntrega) {
		super(id, descripcion, estatusId);
		this.reclamoOrdenEntrega = new ReclamoOrdenEntrega(reclamoOrdenEntrega);
	}
	public OrdenEntrega(String id) {
		super(id);
	}
	public ReclamoOrdenEntrega getReclamoOrdenEntrega() {
		return reclamoOrdenEntrega;
	}
	public void setReclamoOrdenEntrega(ReclamoOrdenEntrega reclamoOrdenEntrega) {
		this.reclamoOrdenEntrega = reclamoOrdenEntrega;
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
