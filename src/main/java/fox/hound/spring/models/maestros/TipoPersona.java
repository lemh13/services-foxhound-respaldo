package fox.hound.spring.models.maestros;

import java.util.ArrayList;

import fox.hound.spring.beans.CustomJsonRootName;

@CustomJsonRootName(plural = "tipoPersona", singular = "tipoPersona")
public class TipoPersona {
	
	private ArrayList<String> tipoPersonas = new ArrayList<String>();
	
	public ArrayList<String> getTipoPersonas() {
		return tipoPersonas;
	}

	public void setTipoPersonas(ArrayList<String> tipoPersonas) {
		this.tipoPersonas = tipoPersonas;
	}

	public TipoPersona() {
		super();
		tipoPersonas.add("V");
		tipoPersonas.add("E");
		tipoPersonas.add("J");
		tipoPersonas.add("G");
		tipoPersonas.add("A");
		tipoPersonas.add("C");
	}
	
	public String nombre(int pos) {
		return tipoPersonas.get(pos);
	}

}
