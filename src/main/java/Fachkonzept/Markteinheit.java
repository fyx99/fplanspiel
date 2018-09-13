package Fachkonzept;

import java.util.ArrayList;
import java.util.List;

public abstract class Markteinheit {

	//DAch für MAteroial, MAschine und Produkt

	private int id;
	protected static int nummer = 1;
	public Markteinheit() {
		// TODO Auto-generated constructor stub
		nummer++;
		this.id = nummer;	//um eine id zu haben
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

	public int getId() {
		return id;
	}

	
}
