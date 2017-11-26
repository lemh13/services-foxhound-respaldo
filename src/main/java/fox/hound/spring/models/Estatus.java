package fox.hound.spring.models;

import java.util.ArrayList;

import fox.hound.spring.beans.CustomJsonRootName;

//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "estatus", singular = "estatus")
public class Estatus {
	
	private ArrayList<String> estatus = new ArrayList<String>();

	public ArrayList<String> getEstatus() {
		return estatus;
	}

	public void setEstatus(ArrayList<String> estatus) {
		this.estatus = estatus;
	}

	public Estatus() {
		super();
		estatus.add("Activo");		// 1
		estatus.add("Suspendido");	// 2
		estatus.add("Eliminado");	// 3
		estatus.add("Aprobado");	// 4
		estatus.add("Rechazado");	// 5
		estatus.add("Pendiente");	// 6

	}
	
	public String nombre(int pos) {
		
	 return estatus.get(pos);
	}
	
}
