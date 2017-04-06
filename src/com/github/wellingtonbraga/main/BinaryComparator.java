package com.github.wellingtonbraga.main;

import java.util.Comparator;

import com.github.wellingtonbraga.individual.Individual;

public class BinaryComparator implements Comparator<Individual> {

	@Override
	public int compare(Individual ind, Individual ind1) {
		return (int) (ind.getFitness() - ind1.getFitness());
	}
}
