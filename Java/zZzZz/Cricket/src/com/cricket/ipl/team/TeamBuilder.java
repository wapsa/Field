package com.cricket.ipl.team;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.cricket.ipl.player.PlayerProbability;
import com.cricket.ipl.player.ShotOutcomes;
import com.cricket.ipl.player.Player;
import com.cricket.ipl.team.Team;

public class TeamBuilder {

	public Team getTeam(int teamNo) {
		String resourceName = "config/team" + teamNo + ".properties";
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream(resourceName));
			List<Player> players = new ArrayList<>();
			for (int i = 1; i <= 11; i++) {
				PlayerProbability<ShotOutcomes> player = new PlayerProbability<>();
				player.add(Double.parseDouble(prop.getProperty("PLAYER" + i + "_DOT_BALL_CHANCE")), ShotOutcomes.DOT);
				player.add(Double.parseDouble(prop.getProperty("PLAYER" + i + "_ONE_RUN_CHANCE")), ShotOutcomes.ONE);
				player.add(Double.parseDouble(prop.getProperty("PLAYER" + i + "_TWO_RUN_CHANCE")), ShotOutcomes.TWO);
				player.add(Double.parseDouble(prop.getProperty("PLAYER" + i + "_THREE_RUN_CHANCE")),
						ShotOutcomes.THREE);
				player.add(Double.parseDouble(prop.getProperty("PLAYER" + i + "_FOUR_RUN_CHANCE")), ShotOutcomes.FOUR);
				player.add(Double.parseDouble(prop.getProperty("PLAYER" + i + "_FIVE_RUN_CHANCE")), ShotOutcomes.FIVE);
				player.add(Double.parseDouble(prop.getProperty("PLAYER" + i + "_SIX_RUN_CHANCE")), ShotOutcomes.SIX);
				player.add(Double.parseDouble(prop.getProperty("PLAYER" + i + "_OUT_CHANCE")), ShotOutcomes.OUT);
				players.add(new Player.Builder().playerName(prop.getProperty("PLAYER" + i + "_NAME"))
						.battingProbability(player).build());
			}

			Team team = new Team.Builder().teamName(prop.getProperty("TEAM_NAME")).players(players).build();
			return team;
		} catch (NullPointerException e) {
			System.out.println("Error. The properties file not found or does not contain the required properties." + e);
			System.exit(1);
		} catch (ClassCastException e) {
			System.out.println("Error. The properties file contains non-string values: " + e);
			System.exit(1);
		} catch (IOException e) {
			System.out.println("IO Exception: " + e);
			System.exit(1);
		}
		throw new RuntimeException("Zzz...");
	}
}
