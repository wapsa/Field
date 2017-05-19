package com.cricket.ipl.player;

public enum ShotOutcomes {
	// All possibilities that can occur when a ball is bowled.
	DOT(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), OUT(0);
	private Integer value;

	ShotOutcomes(Integer value) {
		this.value = value;
	}

	public Integer Value() {
		return value;
	}
}