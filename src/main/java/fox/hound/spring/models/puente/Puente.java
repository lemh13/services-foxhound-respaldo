package fox.hound.spring.models.puente;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import fox.hound.spring.models.Base;

@MappedSuperclass
public class Puente extends Base {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	public Puente(Long id, String estatusId) {
		super(estatusId);
		this.id = id;
	}
	public Puente(String id) {
		this.id = Long.valueOf(id);
	}
	public Puente() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
