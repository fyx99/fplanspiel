package dto;

public class UnternehmenStatDTO {
    
	public UnternehmenStatDTO(double umsatz, double kapital, String name) {
        super();
        this.umsatz = umsatz;
        this.kapital = kapital;
        this.name = name;
    }
	private String name;
    private double umsatz;
	private double kapital;
	private double gewinn;
	
    public double getUmsatz() {
        return umsatz;
    }
    public void setUmsatz(double umsatz) {
        this.umsatz = umsatz;
    }
    public double getKapital() {
        return kapital;
    }
    public void setKapital(double kapital) {
        this.kapital = kapital;
    }
    public double getGewinn() {
        return gewinn;
    }
    public void setGewinn(double gewinn) {
        this.gewinn = gewinn;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
	
	
	

}
