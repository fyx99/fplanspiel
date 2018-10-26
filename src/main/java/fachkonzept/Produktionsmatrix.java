package fachkonzept;

import java.util.HashMap;
import java.util.Map;

public class Produktionsmatrix {

	public Produktionsmatrix(Map<String, Integer> matrix) {
		this.matrix = matrix;
	}
	
	private Map<String, Integer> matrix = new HashMap<String, Integer>();

	public Map<String, Integer> getMatrix() {
		return matrix;
	}


	
	

}
