package com.github.wellingtonbraga.main;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.github.wellingtonbraga.constants.Constants;
import com.github.wellingtonbraga.individual.Individual;

public class GAStart {
	
	private ArrayList<Individual> population;
	
	private int populationCounter;
	
	private Evolve evolve;
	
	public GAStart(){
		evolve = new Evolve();
	}
	public void execute() {
		population = generatePopulation(true);
		Collections.sort(population, new BinaryComparator());
		
		while(Fittest.findFittest(population).getFitness() != 0 && 
				Fittest.findFittest(population).getPointA() == 5) {
			populationCounter++;
			System.out.println("Population: " + populationCounter +
					" | Fittest: " + Fittest.findFittest(population));
			System.out.println("------------------------");
			population = evolve.evolution(population);
		}
		System.out.println("Uma solução foi encontrada:");
		System.out.println("População: " + populationCounter);
		Printer.print(population);
		System.out.println("Fittest: " + Fittest.findFittest(population));
	}
	
	public ArrayList<Individual> generatePopulation(boolean initialize) {
		ArrayList<Individual> population = new ArrayList<Individual>();
		
		if (!initialize) return population;
		
		for (int i = 0; i < Constants.POPULATION_SIZE; i++) {
			Individual individual = new Individual();
			individual.setInfo(generateInfo(i));
			individual.setNumbers(generateNumbers());
			individual.setPointA(new Evolve().generateRandomNumber());
			individual.calculateFitness();
			population.add(individual);
		}
		
		return population;
	}
	
	private ArrayList<Integer> generateNumbers() {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		Random random = new Random();
		
		for (int i = 0; i < Constants.MAX_NUMBERS; i++) {
			int x = random.nextInt(Constants.RANGE);
			while(numbers.contains(x) || x == 0) {
				x = random.nextInt(Constants.RANGE);
			}
			numbers.add(x);
		}
		
		return numbers;
	}
	
	public String generateInfo(int idx) {
		String rawInfo = Integer.toBinaryString(idx);
		return String.format("%7s", rawInfo).replace(' ', '0');
	}
}
