package com.cricket.ipl.player;

import com.cricket.ipl.player.PlayerProbability;
import com.cricket.ipl.player.ShotOutcomes;

public class Player {

	private String playerName;
	private PlayerProbability<ShotOutcomes> battingProbability;
	private int runsScored;
	private int ballsFaced;

	private Player(Builder builder) {
		this.playerName = builder.playerName;
		this.battingProbability = builder.battingProbability;
	}

	public void incrementRunsScored(int incrementBy) {
		runsScored += incrementBy;
	}

	public void incrementBallsFaced() {
		ballsFaced++;
	}

	public String getPlayerName() {
		return playerName;
	}

	public PlayerProbability<ShotOutcomes> getBattingProbability() {
		return battingProbability;
	}

	public int getRunsScored() {
		return runsScored;
	}

	public int getBallsPlayed() {
		return ballsFaced;
	}

	public static class Builder {
		private String playerName;
		private PlayerProbability<ShotOutcomes> battingProbability;

		public Builder playerName(String playerName) {
			this.playerName = playerName;
			return this;
		}

		public Builder battingProbability(PlayerProbability<ShotOutcomes> battingProbability) {
			this.battingProbability = battingProbability;
			return this;
		}

		public Player build() {
			return new Player(this);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("Player [playerName=").append(playerName).append(", battingProbability=")
				.append(battingProbability).append("]");
		return builder2.toString();
	}

}
