package fachkonzept.marketing;

public class Fernsehwerbung extends Marketingmaßnahme {
    
    private static int statVolumen = 16000;
    private static int statEffekt = 55;

    public Fernsehwerbung(String name, double budget) {
        super(name, budget, statVolumen, statEffekt);

    }
    
}
