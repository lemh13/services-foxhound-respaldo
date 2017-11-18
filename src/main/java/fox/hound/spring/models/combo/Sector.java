package fox.hound.spring.models.combo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Inmueble;
import fox.hound.spring.models.Persona;
import fox.hound.spring.models.maestros.Maestro;

@Entity
@Table(name="sector")
@CustomJsonRootName(plural = "sector", singular = "sector")
public class Sector extends Maestro {
	
	@ManyToOne
	@JoinColumn(name="parroquia_id")
	@JsonBackReference(value="sectores-parroquias")
	private Parroquia parroquia;
	
	@OneToMany(mappedBy="sector")
	@JsonManagedReference(value="zonas-sector")
	private List<Zona> zonas;

	@OneToMany(mappedBy="sector")
	@JsonManagedReference(value="inmueble-sector")
	private List<Inmueble> inmuebles;
	
	@OneToMany(mappedBy="sector")
	@JsonManagedReference(value="personas-sector")
	private List<Persona> personas;
	
	public Sector(Long id, String name, int estatusId, String parroquiaId) {
		super(id, name, estatusId);
		this.parroquia = new Parroquia(parroquiaId);
	}
	public Sector(String id) {
		super(id);
	}
	public Sector() {
		super();
	}
	public Parroquia getParroquia() {
		return parroquia;
	}
	public void setParroquia(Parroquia parroquia) {
		this.parroquia = parroquia;
	}
	public List<Zona> getZonas() {
		return zonas;
	}
	public void setZonas(List<Zona> zonas) {
		this.zonas = zonas;
	}
	public List<Inmueble> getInmuebles() {
		return inmuebles;
	}
	public void setInmuebles(List<Inmueble> inmuebles) {
		this.inmuebles = inmuebles;
	}
	public List<Persona> getPersonas() {
		return personas;
	}
	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
	
}