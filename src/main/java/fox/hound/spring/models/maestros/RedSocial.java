package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.puente.RedSocialEmpresa;

@Entity
@Table(name="redSocial")
@CustomJsonRootName(plural = "redSocial", singular = "redSocial")
public class RedSocial extends Maestro {
	
	@ManyToOne
	@JoinColumn(name="redSocialEmpresa_id")
	@JsonBackReference(value="redSocial-redSocialEmpresas")
	private RedSocialEmpresa redSocialEmpresa;

	public RedSocial(Long id, String name, String estatusId, String redSocialEmpresaId) {
		super(id, name, estatusId);
		this.redSocialEmpresa = new RedSocialEmpresa(redSocialEmpresaId);
	}
	public RedSocial() {
		super();
	}
	public RedSocialEmpresa getRedSocialEmpresa() {
		return redSocialEmpresa;
	}
	public void setRedSocialEmpresa(RedSocialEmpresa redSocialEmpresa) {
		this.redSocialEmpresa = redSocialEmpresa;
	}
	
}
