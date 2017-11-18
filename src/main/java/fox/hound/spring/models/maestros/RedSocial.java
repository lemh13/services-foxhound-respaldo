package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Empresa;

@Entity
@Table(name="redSocial")
@CustomJsonRootName(plural = "redSocial", singular = "redSocial")
public class RedSocial extends Maestro {
	
	@ManyToOne
	@JoinColumn(name="empresa_id")
	@JsonBackReference(value="empresa-redSocialEmpresas")
	private Empresa empresa;

	public RedSocial(Long id, String name, int estatusId, String empresa) {
		super(id, name, estatusId);
		this.empresa = new Empresa(empresa);
	}
	public RedSocial() {
		super();
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
}
