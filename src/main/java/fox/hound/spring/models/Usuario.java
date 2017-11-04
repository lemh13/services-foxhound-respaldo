package fox.hound.spring.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;

//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "usuarios", singular = "usuario")
@Entity
public class Usuario {
	// need param encrip : boolean
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String userName;
	@Column(nullable = false)
	private String password;
	@OneToMany(mappedBy="usuario")
	@JsonManagedReference
	private List<Inmueble> inmuebles;

	public Usuario(Long id, String username, String password) {
		super();
		this.id = id;
		this.userName = username;
		this.password = password;
	}
	public Usuario(String id) {
		this.id = Long.valueOf(id);
		this.userName = "";
		this.password = "";
	}
	public Usuario() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String username) {
		this.userName = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
