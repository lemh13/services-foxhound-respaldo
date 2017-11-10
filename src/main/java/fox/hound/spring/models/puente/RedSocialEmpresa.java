package fox.hound.spring.models.puente;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Empresa;
import fox.hound.spring.models.maestros.RedSocial;

@Entity
@Table(name="red_social_empresa")
@CustomJsonRootName(plural = "redSocialEmpresa", singular = "redSocialEmpresa")
public class RedSocialEmpresa extends Puente {

	@OneToMany(mappedBy="redSocialEmpresa")
	@JsonManagedReference(value="redSocial-redSocialEmpresas")
	private List<RedSocial> redSocial;
	
	@ManyToOne
	@JoinColumn(name="empresa_id")
	@JsonBackReference(value="empresa-redSocialEmpresas")
	private Empresa empresa;
	
	public RedSocialEmpresa(Long id, String estatusId, String empresaId) {
		super(id, estatusId);
		this.empresa = new Empresa(empresaId);
	}
	public RedSocialEmpresa(String id) {
		super(id);
	}
	public RedSocialEmpresa() {
		super();
	}
	public List<RedSocial> getRedSocial() {
		return redSocial;
	}
	public void setRedSocial(List<RedSocial> redSocial) {
		this.redSocial = redSocial;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
}
