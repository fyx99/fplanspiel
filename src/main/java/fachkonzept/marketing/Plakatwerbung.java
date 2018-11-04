package fachkonzept.marketing;

public class Plakatwerbung extends Marketingmaßnahme {
    
    private static int statVolumen = 6000;
    private static int statEffekt = 11;

    public Plakatwerbung(String name, double budget) {
        super(name, budget, statVolumen, statEffekt);
        
    }
    //
    

}
