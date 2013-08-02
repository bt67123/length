package com.etcstudio.length;

public class LengthFactory {

	public static Length creatLength(String name, double lengthToM) {
		if(name.equals("mile")) {
	        return new Length("mile", "miles", lengthToM);		
	        
		} else if(name.equals("yard")) {
			return new Length("yard", "yards", lengthToM);
			
		} else if(name.equals("inch")) {
			return new Length("inch", "inches", lengthToM);
			
		} else if(name.equals("foot")) {
			return new Length("foot", "feet", lengthToM);
			
		} else if(name.equals("fath")) {
			return new Length("fath", "faths", lengthToM);
			
		} else if(name.equals("furlong")) {
			return new Length("furlong", "furlongs", lengthToM);
			
		}
		return new Length("", "", 0);
	}
}
