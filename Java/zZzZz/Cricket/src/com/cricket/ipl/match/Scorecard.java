package com.cricket.ipl.match;

import java.util.List;

import com.cricket.ipl.player.Player;
import com.cricket.ipl.team.Team;

public enum Scorecard {

	INSTANCE;

	public void display(Team team) {
		System.out.println("\n\n\n==========================================================================");
		System.out.println("\n" + team.getTeamName() + " Innings Summary");
		System.out.println("==========================================================================");
		System.out.format("%-40s%10s%10s", "Name", "Runs", "Balls");
		System.out.println("\n");
		List<Player> team1Players = team.getPlayers();
		for (int i = 0; i < team1Players.size(); i++) {
			Player player = team1Players.get(i);
			System.out.println(String.format("%-40s%10s%10s", player.getPlayerName(), player.getRunsScored(),
					player.getBallsPlayed()));
		}
		System.out.println("\n");
		System.out.format("%-40s%10s", "Total", team.getTeamScore());
	}

}
