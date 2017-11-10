package fox.hound.spring.models.combo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.maestros.Maestro;
import fox.hound.spring.models.puente.Promocion;

@Entity
@Table(name="descuento")
@CustomJsonRootName(plural = "descuento", singular = "descuento")
public class Descuento extends Maestro {
	
	@Column(nullable = false)
	private double monto;
	@Column(nullable = false)
	private double porcentaje;
	
	@OneToMany(mappedBy="descuento")
	@JsonManagedReference(value="descuento-promociones")
	private List<Promocion> promociones;
	
	public Descuento(Long id, String name, String estatusId, double monto, double porcentaje) {
		super(id, name, estatusId);
		this.monto = monto;
		this.porcentaje = porcentaje;
	}
	public Descuento(String id) {
		super(id);
	}
	public Descuento() {
		super();
	}
	public List<Promocion> getPromociones() {
		return promociones;
	}
	public void setPromociones(List<Promocion> promociones) {
		this.promociones = promociones;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public double getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	
}