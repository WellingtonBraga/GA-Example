package com.github.wellingtonbraga.main;

import java.util.ArrayList;
import java.util.Random;

import com.github.wellingtonbraga.constants.Constants;
import com.github.wellingtonbraga.individual.Individual;

public class Evolve {
	
	public ArrayList<Individual> evolution(ArrayList<Individual> population) {
		ArrayList<Individual> newPopulation = new GAStart().generatePopulation(false);
		
		Individual fittest = Fittest.findFittest(population);
		newPopulation.add(fittest);
		
		for (int i = 1; i < population.size(); i++) {
			Individual individual1 = selectByTournament(population);
			Individual individual2 = selectByTournament(population);
			Individual newIndividual = crossoverOnePoint(individual1, individual2, newPopulation.size());
			newPopulation.add(newIndividual);
		}
		
		for (int i = 0; i < newPopulation.size(); i++) {
			newPopulation.set(i, mutate(newPopulation.get(i)));
		}
		
		return newPopulation;
	}
	
	private Individual mutate(Individual individual) {
		Individual mutant = new Individual();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		for (int i = 0; i < individual.getNumbers().size(); i++) {
			if (Math.random() < Constants.MUTATION_RATE) {
				numbers.add(individual.getNumbers().get(i));
			} else {
				numbers.add(generateRandomNumberNoRepeat(numbers));
			}
		}
		
		mutant.setInfo(individual.getInfo());
		mutant.setNumbers(numbers);
		mutant.setPointA(generateRandomNumber());
		mutant.calculateFitness();
		
		return mutant;
	}
	
	public int generateRandomNumber() {
		Random random = new Random();
		
		int number = random.nextInt(Constants.RANGE);
		
		while(number == 0) {
			number = random.nextInt(Constants.RANGE);
		}
		
		return number;
	}
	
	public int generateRandomNumberNoRepeat(ArrayList<Integer> numbers) {
		Random random = new Random();
		
		int number = random.nextInt(Constants.RANGE);
		
		while(number == 0 || numbers.contains(number)) {
			number = random.nextInt(Constants.RANGE);
		}
		
		return number;
	}
	
	private Individual crossoverOnePoint(Individual individual1, Individual individual2, int infoIdx) {
		Individual newIndividual = new Individual();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		Random random = new Random();
		int point = random.nextInt(Constants.MAX_NUMBERS);
		
		if (Math.random() < Constants.CROSS_RATE) {
			for (int i = 0; i < point; i++) {
				numbers.add(individual1.getNumbers().get(i));
			}
			
			for (int i = numbers.size(); i < Constants.MAX_NUMBERS; i++) {
				numbers.add(individual2.getNumbers().get(i));
			}	
		} else {
			for (int i = 0; i < point; i++) {
				numbers.add(individual2.getNumbers().get(i));
			}
			
			for (int i = numbers.size(); i < Constants.MAX_NUMBERS; i++) {
				numbers.add(individual1.getNumbers().get(i));
			}
		}
		
		newIndividual.setInfo(new GAStart().generateInfo(infoIdx));
		newIndividual.setNumbers(numbers);
		newIndividual.calculateFitness();
		return newIndividual;
	}
	
	private Individual crossover(Individual individual1, Individual individual2, int infoIdx) {
		Individual newIndividual = new Individual();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		for (int i = 0; i < individual1.getNumbers().size(); i++) {
			if (Math.random() < Constants.CROSS_RATE) {
				numbers.add(individual1.getNumbers().get(i));
			} else {
				numbers.add(individual2.getNumbers().get(i));
			}
		}
		
		newIndividual.setInfo(new GAStart().generateInfo(infoIdx));
		newIndividual.setNumbers(numbers);
		newIndividual.calculateFitness();
		
		return newIndividual;
	}
	
	private Individual selectByTournament(ArrayList<Individual> population) {
		Random random = new Random();
		ArrayList<Individual> tournament = new ArrayList<>();
		
		for (int i = 0; i < Constants.TOURNAMENT_SIZE; i++) {
			int idx = random.nextInt(Constants.RANGE_TOURNAMENT);
			tournament.add(population.get(idx));
		}
		
		return Fittest.findFittest(tournament);
	}
}
