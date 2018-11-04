package fachkonzept.marketing;

public class Radiowerbung extends Marketingmaßnahme {
    
    private static int statVolumen = 8000;
    private static int statEffekt = 15;

    public Radiowerbung(String name, double budget) {
        super(name, budget, statVolumen, statEffekt);
        
    }
 

}
