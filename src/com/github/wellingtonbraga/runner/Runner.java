package com.github.wellingtonbraga.runner;

import java.rmi.activation.ActivationGroup_Stub;

import com.github.wellingtonbraga.main.GAStart;

public class Runner {

	public static void main(String[] args) {
		new GAStart().execute();
	}

}
