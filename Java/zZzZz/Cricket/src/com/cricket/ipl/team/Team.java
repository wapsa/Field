package com.cricket.ipl.team;

import java.util.List;

import com.cricket.ipl.player.Player;

public class Team {

	private String teamName;
	private List<Player> players;
	private int teamScore;

	public void incrementTeamScore(int incrementBy) {
		teamScore += incrementBy;
	}

	public String getTeamName() {
		return teamName;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public int getTeamScore() {
		return teamScore;
	}

	private Team(Builder builder) {
		this.teamName = builder.teamName;
		this.players = builder.players;
	}

	public static class Builder {

		private String teamName;
		private List<Player> players;

		public Builder teamName(String teamName) {
			this.teamName = teamName;
			return this;
		}

		public Builder players(List<Player> players) {
			this.players = players;
			return this;
		}

		public Team build() {
			if (players.size() != 11) {
				throw new UnsupportedOperationException("A team must have 11 players.");
			}
			return new Team(this);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("Team [teamName=").append(teamName).append(", players=").append(players).append("]");
		return builder2.toString();
	}

}
