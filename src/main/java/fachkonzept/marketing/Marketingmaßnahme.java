package fachkonzept.marketing;

public abstract class Marketingmaßnahme {
    
    public Marketingmaßnahme(String name, double budget) {
        this.id = nummer = nummer++;
        this.name = name;
        this.budget = budget;
        
    }
    
    //je höher das budget, desto höher die wirkung, 
    
    private double budget;
    private static int nummer;
    private int id;
    private String name;

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
}
