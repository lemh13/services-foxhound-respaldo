package fox.hound.spring.models.maestros;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Servicio;
import fox.hound.spring.models.combo.OrdenEntrega;

@Entity
@Table(name="calificar_servicio")
@CustomJsonRootName(plural = "calificarServicio", singular = "calificarServicio")
public class CalificarServicio extends Maestro {

	@Column(nullable = false)
    private int valor;
	
	@ManyToOne
	@JoinColumn(name="servicio_id")
	@JsonBackReference(value="servicio-calificarServicio")
	private Servicio servicio;
	
	@ManyToOne
	@JoinColumn(name="ordenEntrega_id")
	@JsonBackReference(value="ordenEntrega-calificarServicio")
	private OrdenEntrega ordenEntrega;
	
	@ManyToOne
	@JoinColumn(name="categoriaCalificarServicio_id")
	@JsonBackReference(value="categoriaCalificarServicio-calificarServicios")
	private CategoriaCalificarServicio categoriaCalificarServicio;
	
	public CalificarServicio(Long id, String name, int estatusId, int valor, String servicioId,
			String categoriaCalificarServicio, String ordenEntrega) {
		super(id, name, estatusId);
		this.valor = valor;
		this.servicio = new Servicio(servicioId);
		this.categoriaCalificarServicio = new CategoriaCalificarServicio(categoriaCalificarServicio);
		this.ordenEntrega = new OrdenEntrega(ordenEntrega);
	}
	public CalificarServicio(String id) {
		super(id);
	}
	public CalificarServicio() {
		super();
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	public CategoriaCalificarServicio getCategoriaCalificarServicio() {
		return categoriaCalificarServicio;
	}
	public void setCategoriaCalificarServicio(CategoriaCalificarServicio categoriaCalificarServicio) {
		this.categoriaCalificarServicio = categoriaCalificarServicio;
	}
	public OrdenEntrega getOrdenEntrega() {
		return ordenEntrega;
	}
	public void setOrdenEntrega(OrdenEntrega ordenEntrega) {
		this.ordenEntrega = ordenEntrega;
	}
	
	
	
}
