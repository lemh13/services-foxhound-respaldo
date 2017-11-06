package fox.hound.spring.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.exolab.castor.types.DateTime;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;

//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "TipoInmuebles", singular = "TipoInmueble")
@Entity
public class TipoInmueble {
	// need param encrip : boolean
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Long id_categoria;
	@Column(nullable = false)
	private String descripcion;
	@Column(nullable = false)
	private Date fecha_creacion;
	@Column(nullable = false)
	private Date fecha_modificacion;
	@Column(nullable = false)
	private Integer estatus;
	
	

	public TipoInmueble(Long id, Long id_categoria, String descripcion, Date fecha_creacion, Date fecha_modificacion, Integer estatus) {
		super();
		this.id = id;
		this.id_categoria = id_categoria;
		this.descripcion = descripcion;
		this.fecha_creacion = fecha_creacion;
		this.fecha_modificacion = fecha_modificacion;
		this.estatus = estatus;
	}
	public TipoInmueble(String id) {
		this.id = Long.valueOf(id);
		this.id_categoria = Long.valueOf(id);
		this.descripcion = "";
		this.fecha_creacion = null ;
		this.fecha_modificacion = null;
		this.estatus = Integer.valueOf(id);
		
	}
	public TipoInmueble() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdCategoria() {
		return id_categoria;
	}
	public void setIdCategoria(Long id_categoria) {
		this.id_categoria = id_categoria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaCreacion() {
		return fecha_creacion;
	}
	public void setFechaCreacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public Date getFechaModificacion() {
		return fecha_modificacion;
	}
	public void setFechaModificacion(Date fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}
	public Integer getEstatus() {
		return estatus;
	}
	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

}

