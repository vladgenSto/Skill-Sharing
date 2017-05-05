package domain;

import java.util.ArrayList;

public class Niveles {

	private ArrayList<String> niveles;
	
	public Niveles(){
		niveles=new ArrayList<String>();
		indicaNiveles();
	}
	private void indicaNiveles(){
		niveles.add("Iniciacion");
		niveles.add("Intermedio");
		niveles.add("Experto");
	}
	
	public ArrayList<String> dameNivelesDisponibles(){
		return niveles;
	}
}
