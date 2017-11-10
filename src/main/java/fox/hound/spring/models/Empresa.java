package fox.hound.spring.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.puente.RedSocialEmpresa;

@Entity
@Table(name="empresa")
//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "empresa", singular = "empresa")
public class Empresa extends Base {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String razonSocial;
	@Column(nullable = false)
	private String rif;
	@Column(nullable = false)
	private String quienesSomos;
	@Column(nullable = false)
	private String mision;
	@Column(nullable = false)
	private String vision;
	@Column(nullable = false)
	private String valores;
	@Column(nullable = false)
	private String logo;
	
	@OneToMany(mappedBy="empresa")
	@JsonManagedReference(value="empresa-trabajadores")
	private List<Trabajador> trabajadores;
	
	@OneToMany(mappedBy="empresa")
	@JsonManagedReference(value="empresa-noticias")
	private List<Noticia> noticias;
	
	@OneToMany(mappedBy="empresa")
	@JsonManagedReference(value="empresa-redSocialEmpresas")
	private List<RedSocialEmpresa> redSocialEmpresas;
	
	@OneToMany(mappedBy="empresa")
	@JsonManagedReference(value="empresa-servicios")
	private List<Servicio> servicios;
	
	public Empresa(Long id, String razonSocial, String rif, String quienesSomos, String mision, String vision,
			String valores, String logo, String estatusId) {
		super(estatusId);
		this.id = id;
		this.razonSocial = razonSocial;
		this.rif = rif;
		this.quienesSomos = quienesSomos;
		this.mision = mision;
		this.vision = vision;
		this.valores = valores;
		this.logo = logo;
	}
	public Empresa(String id) {
		this.id = Long.valueOf(id);
	}
	public Empresa() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getRif() {
		return rif;
	}
	public void setRif(String rif) {
		this.rif = rif;
	}
	public String getQuienesSomos() {
		return quienesSomos;
	}
	public void setQuienesSomos(String quienesSomos) {
		this.quienesSomos = quienesSomos;
	}
	public String getMision() {
		return mision;
	}
	public void setMision(String mision) {
		this.mision = mision;
	}
	public String getVision() {
		return vision;
	}
	public void setVision(String vision) {
		this.vision = vision;
	}
	public String getValores() {
		return valores;
	}
	public void setValores(String valores) {
		this.valores = valores;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public List<Trabajador> getTrabajadores() {
		return trabajadores;
	}
	public void setTrabajadores(List<Trabajador> trabajadores) {
		this.trabajadores = trabajadores;
	}
	public List<Noticia> getNoticias() {
		return noticias;
	}
	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}
	public List<RedSocialEmpresa> getRedSocialEmpresas() {
		return redSocialEmpresas;
	}
	public void setRedSocialEmpresas(List<RedSocialEmpresa> redSocialEmpresas) {
		this.redSocialEmpresas = redSocialEmpresas;
	}
	public List<Servicio> getServicios() {
		return servicios;
	}
	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}
	
}
