package fachkonzept;

import fachkonzept.markt.Markteinheit;

public class Kredit extends Markteinheit {
   
    private String name;
    private double volumen; //wv ausgezahlt wird
    private double zinssatz;    //0.05 -> 5 %
    private int laufzeit;    // -> quartale - 4 = ein jahr
    //die tilgungs + zins rate, die pro zeit gezahlt werden muss berechnet sich aus volumen, zinsen und laufzeit
    
        
    public Kredit(double volumen, double zinssatz, int laufzeit, String name) {
        super();
        
        this.volumen = volumen;
        this.zinssatz = zinssatz;
        this.laufzeit = laufzeit;
        this.name = name;
        
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


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
