package fachkonzept.marketing;

public abstract class Marketingmaßnahme {
    
    public Marketingmaßnahme(String name, double budget, int vol, int eff) {
        this.id = nummer;
        nummer++;
        this.name = name;
        this.budget = budget;
        this.volumen = vol;
        this.effektivitaet = eff;
        
    }
    
    //je höher das budget, desto höher die wirkung, 
    
    private int volumen = 0;
    private int effektivitaet = 0;
    private double budget;
    private static int nummer;
    private int id;
    private String name;

    public double getBudget() {
        return budget;
    }

  
    
    public String getName() {
        return name;
    }


    public int getId() {
        return id;
    }
    
    public int getVolumen() {
        return this.volumen;
    }

    public int getEffektivitaet() {
        return effektivitaet;
    }
    
    
}
