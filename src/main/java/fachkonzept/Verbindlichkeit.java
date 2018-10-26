package fachkonzept;

public class Verbindlichkeit {
    
    
    public Verbindlichkeit(Kredit kredit, Double verbleibendeSumme, Integer aktuelleLaufzeit) {
        this.verbleibendeSumme = verbleibendeSumme;
        this.aktuelleLaufzeit = aktuelleLaufzeit;
        this.kredit = kredit;
    }
    
    public Verbindlichkeit(Kredit k) {
        this.kredit = k;
        this.verbleibendeSumme = k.getVolumen();
        this.aktuelleLaufzeit = 0;
    }
    /*
     * klasse um kredite die ein unternehmen hat abzubilden
     * 
     * keine markteinheit -> wird bei kreditaufnahme verwendet
     * */
    private Double verbleibendeSumme;
    private Integer aktuelleLaufzeit;   //wieviele quartale vergangen sind
    private Kredit kredit;
    
    
    public double getVerbleibendeSumme() {
        return verbleibendeSumme;
    }

    public Integer getAktuelleLaufzeit() {
        return aktuelleLaufzeit;
    }

    public Kredit getKredit() {
        return kredit;
    }

    
    public double tilgen(double rate) { //nur für nicht außerordentliche tilgung sprich 1mal pro runde
        this.verbleibendeSumme -= rate;
        this.aktuelleLaufzeit++;
        return rate;    //method chaining
    }
    
    

}
