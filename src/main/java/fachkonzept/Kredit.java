package fachkonzept;

import fachkonzept.markt.Markteinheit;
import fachkonzept.util.KreditArt;
import fachkonzept.util.SimulationsKonstanten;

public class Kredit extends Markteinheit {
   
    private KreditArt kreditArt;
    private double volumen; //wv ausgezahlt wird
    private double zinssatz;    //0.05 -> 5 %
    private int laufzeit;    // -> quartale - 4 = ein jahr
    private int verwaltungsZeit = 0;
    //die tilgungs + zins rate, die pro zeit gezahlt werden muss berechnet sich aus volumen, zinsen und laufzeit
    
        
    public Kredit(double volumen, double zinssatz, int laufzeit, KreditArt kreditArt) {
        super();
        
        this.volumen = volumen;
        this.zinssatz = zinssatz;
        this.laufzeit = laufzeit;
        this.kreditArt = kreditArt;
        this.setName(kreditArt.name());
        this.verwaltungsZeit = (int)(volumen * SimulationsKonstanten.VERWALTUNGSMINUTEN_PRO_EURO_KREDIT);
    }


    public double getVolumen() {
        return volumen;
    }


    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }


    public double getZinssatz() {
        return zinssatz;
    }


    public void setZinssatz(double zinssatz) {
        this.zinssatz = zinssatz;
    }


    public int getLaufzeit() {
        return laufzeit;
    }


    public void setLaufzeit(int laufzeit) {
        this.laufzeit = laufzeit;
    }


	public KreditArt getKreditArt() {
		return kreditArt;
	}


	public void setKreditArt(KreditArt kreditArt) {
		this.kreditArt = kreditArt;

        this.setName(kreditArt.name());
	}


	public int getVerwaltungsZeit() {
		return verwaltungsZeit;
	}



    
}
