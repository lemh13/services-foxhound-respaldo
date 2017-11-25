package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Servicio;
import fox.hound.spring.models.puente.CargoTipoServicio;

@Entity
@Table(name="tipo_servicio")
@CustomJsonRootName(plural = "tipoServicio", singular = "tipoServicio")
public class TipoServicio extends Maestro {
	
	// SELECT s FROM servicio s, tipo_servicio t, categoria c WHERE s.tipo_servicio.id == t.id AND t.categoria.id = :id
	
	@ManyToOne
	@JoinColumn(name="categoria_id")
	@JsonBackReference(value="tiposervicio-categoria")
	private Categoria categoria;
	@Transient
	private Long padre_id;
	@Transient
	private String padre_descripcion;
	

	@Column(nullable = false)
    private String imagenServicio;
	
	@OneToMany(mappedBy="tipoServicio")
	@JsonManagedReference(value="servicio-tipoServicio")
	private List<Servicio> servicios;
	
	@OneToMany(mappedBy="tipoServicio")
	@JsonManagedReference(value="cargoTipoServicio-tipoServicio")
	private List<CargoTipoServicio> cargoTipoServicios;
	
	public TipoServicio(Long id, String name, int estatusId, String imagenServicio, String categoriaId) {
		super(id, name, estatusId);
		this.imagenServicio = imagenServicio;
		this.categoria = new Categoria(categoriaId);
	}
	public TipoServicio(String id) {
		super(id);
	}
	public TipoServicio() {
		
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getImagenServicio() {
		return imagenServicio;
	}
	public void setImagenServicio(String imagenServicio) {
		this.imagenServicio = imagenServicio;
	}
	public List<Servicio> getServicios() {
		return servicios;
	}
	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}
	public List<CargoTipoServicio> getCargoTipoServicios() {
		return cargoTipoServicios;
	}
	public void setCargoTipoServicios(List<CargoTipoServicio> cargoTipoServicios) {
		this.cargoTipoServicios = cargoTipoServicios;
	}
	public Long getCategoria_id() {
		return categoria.getId();
	}
	public void setCategoria_id(Long categoria_id) {
		this.padre_id = categoria_id;
	}
	public String getCategoria_descripcion() {
		return categoria.getDescripcion();
	}
	public void setCategoria_descripcion(String categoria_descripcion) {
		this.padre_descripcion = categoria_descripcion;
	}

}
