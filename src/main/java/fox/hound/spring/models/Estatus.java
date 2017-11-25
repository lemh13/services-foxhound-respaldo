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
		estatus.add("Activo");
		estatus.add("Suspendido");
		estatus.add("Eliminado");
		estatus.add("Aprobado");
		estatus.add("Rechazado");
		estatus.add("Pendiente");

	}
	
	public String nombre(int pos) {
		
	 return estatus.get(pos);
	}
	
}
