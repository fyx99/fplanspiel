package fachkonzept;

public class Arbeitskraft {
    
    private int auslastung; //wv minuten er beschäftigt ist
    private Mitarbeiter m;
    
    public Arbeitskraft(int auslastung, Mitarbeiter m) {
        super();
        this.auslastung = auslastung;
        this.m = new Mitarbeiter(m);
    }
    public Arbeitskraft(Mitarbeiter m) {
        this.m = m;
        this.auslastung = 0;
    }
    public int getAuslastung() {
        return auslastung;
    }
    public void setAuslastung(int auslastung) {
        this.auslastung = auslastung;
    }
    public Mitarbeiter getM() {
        return m;
    }
    public void setM(Mitarbeiter m) {
        this.m = m;
    }
    public void auslastungErhoeen(int zeit) {
        this.auslastung += zeit;
    }

    public boolean isAusgelastet() {
        if(auslastung >= m.getArbeitszeit()) {
            return true;
        }
        return false;
    }
    
}
