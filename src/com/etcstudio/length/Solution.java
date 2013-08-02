package com.etcstudio.length;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Solution {
	
	public List<Length> lengths;
	
	public static final String INPUT_FILE_NAME  = 
			"input.txt";
	
	public static final String OUTPUT_FILE_NAME =
			"output.txt";
	
	public static final String E_MAIL = "350481693@qq.com";
	
	public Solution() {
		try {
			lengths = getLengths(INPUT_FILE_NAME);
			for(Length length : lengths) {
				System.out.println(length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Length getLength(String name, double lengthToM) {
		Length length = LengthFactory.creatLength(name, lengthToM);
		return length;
	}

	public void outputAnswer() throws IOException {
	    FileWriter writer = new FileWriter(new File(OUTPUT_FILE_NAME));	
	    writer.write(getAllAnswersString());
	    writer.close();
	}

	public String getAllAnswersString() throws IOException {
	    StringBuilder stringBuilder = new StringBuilder();	
	    stringBuilder.append(E_MAIL);
	    stringBuilder.append("\n\n");
	    
	    List<Double> answers = getAllAnswers();
	    for(Double answer : answers) {
	    	stringBuilder.append(String.format("%.2f m", answer));
	    	stringBuilder.append("\n");
	    }
	    stringBuilder.delete(
	    		stringBuilder.length()-1, 
	    		stringBuilder.length());
	    return stringBuilder.toString();
	}

	public List<Double> getAllAnswers() throws IOException{
		List<Double> answers = new ArrayList<Double>();
		List<String> problems = getProblems();
		problems.remove(problems.size() - 1);
		for(String problem : problems) {
			answers.add(answer(problem));
		}
		return answers;
	}

	public List<String> getProblems() 
			throws IOException {
		
		List<String> problems = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(
				new FileReader(INPUT_FILE_NAME));
		String s;
		boolean isProblem = false;
		while((s = reader.readLine()) != null) {
			if(isProblem) {
				problems.add(s);
			}
			if(s.equals("")) {
				isProblem = true;
			}
		}
		reader.close();
		return problems;
	}

	public Double answer(String problem) throws IOException {
		String[] split = problem.split(" ");
		List<Double> numbers = new ArrayList<Double>();
		List<String> lengthTypes = new ArrayList<String>();
		List<String> operators = new ArrayList<String>();
		for(String s : split) {
			if(isNumber(s)) {
				numbers.add(Double.parseDouble(s));
			} else if(isOperator(s)) {
				operators.add(s);
			} else {
				lengthTypes.add(s);
			}
		}
		
		System.out.println(numbers);
		System.out.println(lengthTypes);
		System.out.println(operators);
		
		List<Double> mNumbers = getM(numbers, lengthTypes);
		double total = mNumbers.get(0);
		for(int i = 0; i < operators.size(); i++) {
			if(operators.get(i).equals("+")) {
				total += mNumbers.get(i + 1);
			} else if(operators.get(i).equals("-")) {
				total -= mNumbers.get(i + 1);
			}
		} 
		return total;
	}

	public boolean isNumber(String string) {
		try {
			Double.parseDouble(string);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isOperator(String string) {
		if(string.equals("+") || string.equals("-")) {
			return true;
		}
		return false;
	}
	
	public List<Double> getM(List<Double> numbers, List<String> types) throws IOException {
		List<Double> mNumbers = new ArrayList<Double>();
		for(int i = 0; i < types.size(); i++) {
			for(Length length : lengths) {
				if(types.get(i).equals(length.getName()) ||
				   types.get(i).equals(length.getDoubleName())) {
					mNumbers.add(numbers.get(i) * length.getLengthToM());
					continue;
				}
			}
		}
		return mNumbers;
	}

	public List<Length> getLengths(String filename) 
			throws IOException {
		
		lengths = new ArrayList<Length>();
	    BufferedReader reader = new BufferedReader(
	    		new FileReader(filename));
	    String s;
	    while(!(s = reader.readLine()).equals("")) {
	    	String[] split = s.split(" ");
	        lengths.add(getLength(split[1], Double.parseDouble(split[3])));
	    }
	    reader.close();
	    return lengths;
	}

}
