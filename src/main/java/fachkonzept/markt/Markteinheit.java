package fachkonzept.markt;

import java.util.ArrayList;
import java.util.List;

public abstract class Markteinheit {

	//DAch für MAteroial, MAschine und Produkt

	private int id;
	private String name;
	protected static int nummer = 0;
	public Markteinheit() {
		// TODO Auto-generated constructor stub

		this.id = nummer;	//um eine id zu haben
	    nummer++;
		alleMarkteinheiten.add(this);
	}

	protected static List<Markteinheit> alleMarkteinheiten = new ArrayList<>();
	
	public static Markteinheit findeMarkteinheit(int id) {
		for (int i = 0; i < alleMarkteinheiten.size(); i++) {
			if(alleMarkteinheiten.get(i).getId() == id)
				return alleMarkteinheiten.get(i);
		}
		return null;
	}
	
	public static Markteinheit findeMarkteinheit(String name) {
		for(Markteinheit m : Markteinheit.alleMarkteinheiten) {
			if(m.getName().equals(name)) {
				return m;
			}
		}
		return null;
	}
	
	public static Markteinheit findeMarkteinheit(String name, Class<?> klasse) {	//mit check der klasse
		klasse.getName();
		for(Markteinheit m : Markteinheit.alleMarkteinheiten) {
			if(m.getClass().getName().equals(klasse.getName()) && m.getName() == name) {
				return m;
			}
		}
		return null;
	}
	

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

    public void setName(String name) {
        this.name = name;
    }
	

	
}
