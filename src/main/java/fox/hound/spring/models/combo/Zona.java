package fox.hound.spring.models.combo;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.maestros.Maestro;

@Entity
@Table(name="zona")
@CustomJsonRootName(plural = "zona", singular = "zona")
public class Zona extends Maestro {
	
	@ManyToOne
	@JoinColumn(name="sector_id")
	@JsonBackReference
	private Sector sector;
	
	//ManyToOne
	//Servicio

	public Zona(Long id, String name, String estatusId, String sectorId) {
		super(id, name, estatusId);
		this.sector = new Sector(sectorId);
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
	
}