package com.cricket.ipl.player;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class PlayerProbability<E> {

	private final NavigableMap<Double, E> map = new TreeMap<Double, E>();
	private final Random random;
	private double total = 0;

	public PlayerProbability() {
		this(new Random());
	}

	public PlayerProbability(Random random) {
		this.random = random;
	}

	public void add(double weight, E result) {
		if (weight <= 0)
			return;
		total += weight;
		map.put(total, result);
	}

	public E next() {
		double value = random.nextDouble() * total;
		return map.ceilingEntry(value).getValue();
	}

	@Override
	public String toString() {
		return map.toString();
	}
}

