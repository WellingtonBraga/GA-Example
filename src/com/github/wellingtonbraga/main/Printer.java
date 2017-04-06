package com.github.wellingtonbraga.main;

import java.util.ArrayList;

import com.github.wellingtonbraga.individual.Individual;

public class Printer {
	
	public static void print(ArrayList<Individual> population) {
		for (int i = 0; i < population.size(); i++) {
			System.out.println(population.get(i));
		}
	}
	
}
