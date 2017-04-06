package com.github.wellingtonbraga.individual;

import java.util.ArrayList;

import com.github.wellingtonbraga.constants.Constants;

public class Individual {
	
	private String info;
	
	private ArrayList<Integer> numbers;
	
	private int pointA;
	
	private int fitness;
	
	private int sum;
	
	public void calculateFitness() {
		int total = 0;
		int p = pointA - 1;
		for(int i = 0; i <= p; i++) {
			total += numbers.get(i);
		}
		
		if (total == Constants.DESIRED) {
			fitness = 0;
		} else if (total < Constants.DESIRED) {
			fitness = Constants.DESIRED - total;
		}else {
			fitness = total - Constants.DESIRED;
		}
		
		if(pointA >= numbers.size()) {
			return;
		}
		
		total = numbers.get(pointA);
		p = pointA + 1;
		
		for(int i = p; i < numbers.size(); i++) {
			total *= numbers.get(i);
		}
		
		if (total < Constants.DESIREDB) {
			fitness += Constants.DESIREDB - total;
		}else {
			fitness += total - Constants.DESIREDB;
		}
		
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public ArrayList<Integer> getNumbers() {
		return numbers;
	}

	public void setNumbers(ArrayList<Integer> numbers) {
		this.numbers = numbers;
	}

	public int getFitness() {
		return fitness;
	}
	
	public int getPointA() {
		return pointA;
	}

	public void setPointA(int pointA) {
		this.pointA = pointA;
	}

	@Override
	public String toString(){
		return "Individual [info=" + info
				+ ", pointA=" + pointA
				+ ", value={" + numbers.get(0) + ","  + numbers.get(1) + ","  
				+ numbers.get(2) + ","  + numbers.get(3) + ","  + numbers.get(4) + ","  + numbers.get(5)
				+ ","  + numbers.get(6) + ","  + numbers.get(7) + ","  + numbers.get(8)
				+ ","  + numbers.get(9) + "}"  
				+ ", fitness=" + fitness + "]";
	}
}
