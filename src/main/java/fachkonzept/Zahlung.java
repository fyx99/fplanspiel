package fachkonzept;

public class Zahlung {
    
    private double summe;   //wv
    private int runde;      //wann
    private String beschreibung;    //was
    public Zahlung(double summe, int runde, String beschreibung) {
        super();
        this.summe = summe;
        this.runde = runde;
        this.beschreibung = beschreibung;
    }
    public double getSumme() {
        return summe;
    }
    public void setSumme(double summe) {
        this.summe = summe;
    }
    public int getRunde() {
        return runde;
    }
    public void setRunde(int runde) {
        this.runde = runde;
    }
    public String getBeschreibung() {
        return beschreibung;
    }
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
    
    

}
