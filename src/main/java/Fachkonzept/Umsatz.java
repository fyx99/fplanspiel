package Fachkonzept;

public class Umsatz {
	
	Angebot angebot;
	int menge;
	int runde;		//!!! umsatz wird am anfang jeder neuen runde generiert - deswegen - 1 brachten
	
	public Umsatz(Angebot a, int menge, int runde) {
		super();
		this.angebot = a;
		this.menge = menge;
		this.runde = runde;
	}

	

	public Angebot getAngebot() {
		return angebot;
	}



	public void setAngebot(Angebot angebot) {
		this.angebot = angebot;
	}



	public int getMenge() {
		return menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
	}

	public int getRunde() {
		return runde;
	}

	public void setRunde(int runde) {
		this.runde = runde;
	}
	
	

}
