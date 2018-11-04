package fachkonzept.marketing;

public class Sponsoring extends Marketingmaßnahme {

    private static int statVolumen = 12000;
    private static int statEffekt = 30;

    public Sponsoring(String name, double budget) {
        super(name, budget, statVolumen, statEffekt);
        
    }
    

}
