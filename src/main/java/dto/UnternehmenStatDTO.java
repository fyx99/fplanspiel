package dto;

public class UnternehmenStatDTO {
    
	public UnternehmenStatDTO(double umsatz, double kapital, String name, double gewinn) {
        super();
        this.umsatz = umsatz;
        this.kapital = kapital;
        this.name = name;
        this.gewinn = gewinn;
    }
	private String name;
    private double umsatz;
	private double kapital;
	private double gewinn;		//Gesamter Gewinn
	
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
