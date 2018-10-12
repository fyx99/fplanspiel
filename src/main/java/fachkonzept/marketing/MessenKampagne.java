package fachkonzept.marketing;

public class MessenKampagne extends Marketingmaßnahme{

    private static int statVolumen = 4000;
    private static int statEffekt = 12;
    
    public MessenKampagne(String name, double budget) {
        super(name, budget, statVolumen, statEffekt);
        
    }
    
    

}
