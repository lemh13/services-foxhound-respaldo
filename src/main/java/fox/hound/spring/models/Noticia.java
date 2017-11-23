package fox.hound.spring.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.maestros.Maestro;

@Entity
@Table(name="noticia")
@CustomJsonRootName(plural = "noticia", singular = "noticia")
public class Noticia extends Maestro {

	@Column(nullable = false)
	private String titulo;
	@Column(nullable = false)
	private Date fecha_caducidad;
	@Column(nullable = false)
	private String imgNoticia;
	@Transient
	private Long dias_caducidad; 
	
	@ManyToOne
	@JoinColumn(name="empresa_id")
	@JsonBackReference(value="empresa-noticias")
	private Empresa empresa;
	
	public Noticia() {
		super();
	}
	public Noticia(Long id, String descripcion, int estatusId, String titulo, String imgNoticia, String empresaId,
			Date fecha_caducidad) {
		super(id, descripcion, estatusId);
		this.imgNoticia = imgNoticia;
		this.titulo = titulo;
		this.fecha_caducidad = fecha_caducidad;
		this.empresa = new Empresa(empresaId);
	}
	public Noticia(String id) {
		super(id);
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public String getImgNoticia() {
		return imgNoticia;
	}
	public void setImgNoticia(String imgNoticia) {
		this.imgNoticia = imgNoticia;
	}
	public Date getFecha_caducidad() {
		return fecha_caducidad;
	}
	public void setFecha_caducidad(Date fecha_caducidad) {
		this.fecha_caducidad = fecha_caducidad;
	}
	public Long getDias_caducidad() {
		long diff = Math.abs(getFecha_creacion().getTime() - getFecha_caducidad().getTime());
		long diffDays = diff / (24 * 60 * 60 * 1000);
		return diffDays;
	}
	public void setDias_caducidad(Long dias_caducidad) {
		this.dias_caducidad = dias_caducidad;
	}
	

}
