package fachkonzept;

public class Umsatz {
	
	private Angebot angebot;
	private int menge;
	private int runde;
	private Unternehmen verkaeufer;
	
	
    public Umsatz(Angebot a, int menge, int runde, Unternehmen verkaeufer) {
        this.angebot = a;
        this.menge = menge;
        this.runde = runde;
        this.verkaeufer = verkaeufer;
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

    public Unternehmen getVerkaeufer() {
        return verkaeufer;
    }

    public void setVerkaeufer(Unternehmen verkaeufer) {
        this.verkaeufer = verkaeufer;
    }
	
	

}
