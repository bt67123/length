package com.etcstudio.length;


public class Length {
    private String name;
    private String doubleName;
    private double lengthToM;
    
	public Length(String name, String doubleName, double lengthToM) {
		super();
		this.name = name;
		this.doubleName = doubleName;
		this.lengthToM = lengthToM;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDoubleName() {
		return doubleName;
	}

	public void setDoubleName(String doubleName) {
		this.doubleName = doubleName;
	}

	public double getLengthToM() {
		return lengthToM;
	}

	public void setLengthToM(double lengthToM) {
		this.lengthToM = lengthToM;
	}
    
}
