package fox.hound.spring.models.puente;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Trabajador;
import fox.hound.spring.models.Visita;

@Entity
@Table(name="trabajador_visita")
@CustomJsonRootName(plural = "trabajadorVisita", singular = "trabajadorVisita")
public class TrabajadorVisita extends Puente {

	@ManyToOne
	@JoinColumn(name="visita_id")
	@JsonBackReference(value="visita-trabajadorVisitas")
	private Visita visita;
	
	@ManyToOne
	@JoinColumn(name="trabajador_id")
	@JsonBackReference(value="trabajador-TrabajadorVisitas")
	private Trabajador trabajador;
	
	public TrabajadorVisita() {
		super();
	}
	public TrabajadorVisita(Long id, String estatusId, String visita, String trabajador) {
		super(id, estatusId);
		this.visita = new Visita(visita);
		this.trabajador = new Trabajador(trabajador);
	}
	public Visita getVisita() {
		return visita;
	}
	public void setVisita(Visita visita) {
		this.visita = visita;
	}
	public Trabajador getTrabajador() {
		return trabajador;
	}
	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}
	public TrabajadorVisita(String id) {
		super(id);
	}
	
	
}
