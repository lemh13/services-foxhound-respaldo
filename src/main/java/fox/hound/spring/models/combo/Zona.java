package fox.hound.spring.models.combo;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Servicio;
import fox.hound.spring.models.maestros.Maestro;

@Entity
@Table(name="zona")
@CustomJsonRootName(plural = "zona", singular = "zona")
public class Zona extends Maestro {
	
	@ManyToOne
	@JoinColumn(name="sector_id")
	@JsonBackReference(value="zonas-sector")
	private Sector sector;
	
	@ManyToOne
	@JoinColumn(name="servicio_id")
	@JsonBackReference(value="servicio-zonas")
	private Servicio servicio;

	public Zona(Long id, String name, int estatusId, String sectorId, String servicioId) {
		super(id, name, estatusId);
		this.sector = new Sector(sectorId);
		this.servicio = new Servicio(servicioId);
	}
	public Zona(String id) {
		super(id);
	}
	public Zona() {
		super();
	}
	public Sector getSector() {
		return sector;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
}