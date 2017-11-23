package fox.hound.spring.print;

public class PersonaGlobal {
	
	private Long id;
	private String nombre;
	private int identificacion;
	private String password;
	private String email;
	private int tipoPersona;
	private Long rol;
	
	public PersonaGlobal(Long id, String nombre, int identificacion, String password, String email, int tipoPersona,
			Long rol) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.identificacion = identificacion;
		this.password = password;
		this.email = email;
		this.tipoPersona = tipoPersona;
		this.rol = rol;
	}

	public PersonaGlobal() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(int identificacion) {
		this.identificacion = identificacion;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(int tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public Long getRol() {
		return rol;
	}

	public void setRol(Long rol) {
		this.rol = rol;
	}
	
	

}
