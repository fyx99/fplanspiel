package dto;

import fachkonzept.util.MarketingmaßnahmenArt;

public class MarketingmaßnahmeDTO {

	private Double budget;
	private String name;
	private MarketingmaßnahmenArt art;
	private Integer id;
	
	
	public Double getBudget() {
		return budget;
	}


	public void setBudget(Double budget) {
		this.budget = budget;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public MarketingmaßnahmenArt getArt() {
		return art;
	}


	public void setArt(MarketingmaßnahmenArt art) {
		this.art = art;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}



}
