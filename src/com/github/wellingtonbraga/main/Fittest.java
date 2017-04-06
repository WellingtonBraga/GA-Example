package com.github.wellingtonbraga.main;

import java.util.ArrayList;

import com.github.wellingtonbraga.individual.Individual;

public class Fittest {
	
	public static Individual findFittest(ArrayList<Individual> population) {
		Individual fittest;
		fittest = population.get(0);
		
		for (int i = 0; i < population.size(); i++) {
			if (fittest.getFitness() >= population.get(i).getFitness()){
				fittest = population.get(i);
			}
		}
		
		return fittest;
	}
}
