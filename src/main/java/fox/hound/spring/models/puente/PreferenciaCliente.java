package fox.hound.spring.models.puente;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Cliente;
import fox.hound.spring.models.maestros.Categoria;
import fox.hound.spring.models.maestros.TipoServicio;
import fox.hound.spring.models.maestros.TipoCaracteristica;

@Entity
@Table(name="preferencia_cliente")
@CustomJsonRootName(plural = "preferenciaCliente", singular = "preferenciaCliente")
public class PreferenciaCliente extends Puente {
	
	@ManyToOne
	@JoinColumn(name="tipoCaracteristicaId", nullable = true)
	@JsonBackReference(value="tipoCaracteristica-preferenciaCliente")
	private TipoCaracteristica tipoCaracteristica;
	
	@ManyToOne
	@JoinColumn(name="categoriaId", nullable = true)
	@JsonBackReference(value="categoria-preferenciaCliente")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name="tipoServicioId", nullable = true)
	@JsonBackReference(value="tipoServicio-preferenciaCliente")
	private TipoServicio tipoServicio;
	
	@ManyToOne
	@JoinColumn(name="clienteId", nullable = false)
	@JsonBackReference(value="cliente-preferenciaCliente")
	private Cliente cliente;
	
	public PreferenciaCliente() {
		super();
	}
	public PreferenciaCliente(Long id, int estatusId, String tipoCaracteristicaId,
			String clienteId, String tipoServicioId, String categoriaId) {
		super(id, estatusId);
		this.tipoCaracteristica = new TipoCaracteristica(tipoCaracteristicaId);
		this.cliente = new Cliente(clienteId);
		this.tipoServicio = new TipoServicio(tipoServicioId);
		this.categoria = new Categoria(categoriaId);
	}
	public PreferenciaCliente(String id) {
		super(id);
	}
	public TipoCaracteristica getTipoCaracteristica() {
		return tipoCaracteristica;
	}
	public void setTipoCaracteristica(TipoCaracteristica tipoCaracteristica) {
		this.tipoCaracteristica = tipoCaracteristica;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	
	
}
