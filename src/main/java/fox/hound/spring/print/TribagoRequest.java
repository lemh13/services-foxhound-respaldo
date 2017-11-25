package fox.hound.spring.print;

public class TribagoRequest {
	
	private Long tipoInmueble;
	private Long tipoServicio;
	private Long tipoCategoria;
	
	public TribagoRequest(Long tipoInmueble, Long tipoServicio, Long tipoCategoria) {
		super();
		this.tipoInmueble = tipoInmueble;
		this.tipoServicio = tipoServicio;
		this.tipoCategoria = tipoCategoria;
	}
	public TribagoRequest() {
		
	}
	public Long getTipoInmueble() {
		return tipoInmueble;
	}
	public void setTipoInmueble(Long tipoInmueble) {
		this.tipoInmueble = tipoInmueble;
	}
	public Long getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(Long tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	public Long getTipoCategoria() {
		return tipoCategoria;
	}
	public void setTipoCategoria(Long tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}
	
	

}
