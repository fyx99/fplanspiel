package dto;

public class UmsatzDTO {

	private AngebotDTO angebot;
	private Integer menge;
	private Integer runde;
	private String verkaeufer;
	
	public AngebotDTO getAngebot() {
		return angebot;
	}
	public void setAngebot(AngebotDTO angebot) {
		this.angebot = angebot;
	}
	public Integer getMenge() {
		return menge;
	}
	public void setMenge(Integer menge) {
		this.menge = menge;
	}
	public Integer getRunde() {
		return runde;
	}
	public void setRunde(Integer runde) {
		this.runde = runde;
	}
	public String getVerkaeufer() {
		return verkaeufer;
	}
	public void setVerkaeufer(String verkaeufer) {
		this.verkaeufer = verkaeufer;
	}
	
	

}
