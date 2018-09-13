package Fachkonzept;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Produktionsmatrix {

	public Produktionsmatrix(Map<String, Integer> matrix) {
		this.matrix = matrix;
	}
	
	private Map<String, Integer> matrix = new HashMap<String, Integer>();

	public Map<String, Integer> getMatrix() {
		return matrix;
	}

	public void setMatrix(Map<String, Integer> matrix) {
		this.matrix = matrix;
	}

	
	

}
