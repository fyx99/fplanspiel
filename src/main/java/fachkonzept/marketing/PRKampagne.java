package fachkonzept.marketing;

public class PRKampagne extends Marketingmaßnahme {
    
    private static int statVolumen = 4000;
    private static int statEffekt = 25;

    public PRKampagne(String name, double budget) {
        super(name, budget, statVolumen, statEffekt);
       
    }
  

}
