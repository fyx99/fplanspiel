package fachkonzept;

public class SimulationsSet {
	
	//pro Produkt
	
	private double startPreis = 0;
	private double wachstumsFaktor = 1;	//veränderung pro quartal

	public SimulationsSet() {
		// TODO Auto-generated constructor stub
	}

    public double getStartPreis() {
        return startPreis;
    }

    public void setStartPreis(double startPreis) {
        this.startPreis = startPreis;
    }

    public double getWachstumsFaktor() {
        return wachstumsFaktor;
    }

    public void setWachstumsFaktor(double wachstumsFaktor) {
        this.wachstumsFaktor = wachstumsFaktor;
    }
	
	
	
}
