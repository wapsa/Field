package com.cricket.ipl.match;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cricket.ipl.player.ShotOutcomes;
import com.cricket.ipl.player.Player;
import com.cricket.ipl.team.Team;
import com.cricket.ipl.team.TeamBuilder;

public class Match {

	private static int OVERS_IN_MATCH = 2;

	public void simulate() {
		Team team1 = new TeamBuilder().getTeam(1);
		Team team2 = new TeamBuilder().getTeam(2);
		int team1Score = simulateTeamBatting(team1, null, team2);
		team1.incrementTeamScore(team1Score);
		Scorecard.INSTANCE.display(team1);
		int team2Score = simulateTeamBatting(team2, team1Score + 1, team1);
		team2.incrementTeamScore(team2Score);
		Scorecard.INSTANCE.display(team2);
	}

	private int simulateTeamBatting(Team team, Integer target, Team opponentTeam) {
		System.out.println("\n\n\nStart of " + team.getTeamName() + " innings...\n");
		List<Player> team1Players = team.getPlayers();
		Map<String, Player> onFieldBatsmen = new HashMap<>();
		onFieldBatsmen.put("striker", team1Players.get(0));
		onFieldBatsmen.put("non-striker", team1Players.get(1));
		int batsmanCount = 1;
		int runsScored = 0;
		int ballsLeft = OVERS_IN_MATCH * 6;

		innings: for (int ball = 1; ball <= OVERS_IN_MATCH * 6; ball++) {
			if (target != null && runsScored >= target) {
				System.out.println(team.getTeamName() + " have chased down " + target + " with " + (11 - batsmanCount)
						+ " wickets and " + ballsLeft + " balls in hand.");
				break innings;
			}
			if (ball != 1 && ballsLeft % 6 == 0) {
				rotateStrike(onFieldBatsmen);
			}
			Player onStrikeBatsman = onFieldBatsmen.get("striker");
			if (onStrikeBatsman != null) {
				ballsLeft -= 1;
				ShotOutcomes shotOutcome = onStrikeBatsman.getBattingProbability().next();
				runsScored += shotOutcome.Value();
				onStrikeBatsman.incrementBallsFaced();
				onStrikeBatsman.incrementRunsScored(shotOutcome.Value());
				switch (shotOutcome) {
				case DOT:
					System.out.println((ball / 6) + "." + (ball % 6) + "  " + onStrikeBatsman.getPlayerName()
							+ " on strike. No Runs.");
					break;
				case TWO:
				case FOUR:
				case SIX:
					System.out.println((ball / 6) + "." + (ball % 6) + "  " + onStrikeBatsman.getPlayerName()
							+ " scores " + shotOutcome.Value() + " runs.");
					break;
				case ONE:
				case THREE:
				case FIVE:
					System.out.println((ball / 6) + "." + (ball % 6) + "  " + onStrikeBatsman.getPlayerName()
							+ " scores " + shotOutcome.Value() + " runs.");
					rotateStrike(onFieldBatsmen);
					break;
				case OUT:
					if (batsmanCount == 9) {
						// All Out
						if (target != null) {
							// Chasing a target.
							System.out.println((ball / 6) + "." + (ball % 6) + "  " + onStrikeBatsman.getPlayerName()
									+ " has fallen for " + onStrikeBatsman.getRunsScored() + "("
									+ onStrikeBatsman.getBallsPlayed() + ") and that ends the innings for "
									+ team.getTeamName() + " falling short by " + (target - runsScored) + " runs. "
									+ opponentTeam.getTeamName() + " have won.\n\n");
						} else {
							// Batting first.
							System.out.println((ball / 6) + "." + (ball % 6) + "  " + onStrikeBatsman.getPlayerName()
									+ " has fallen for " + onStrikeBatsman.getRunsScored() + "("
									+ onStrikeBatsman.getBallsPlayed() + ") and that ends the innings for "
									+ team.getTeamName() + ". " + opponentTeam.getTeamName() + " needs "
									+ (runsScored + 1) + " runs to win.");
							System.out.println(
									"----------------------------------------------------------------------------------------- \n");
						}
						break innings;
					} else {
						// next batsman to bat
						Player nextBatsmanIn = team1Players.get(++batsmanCount);
						System.out.println(
								(ball / 6) + "." + (ball % 6) + "  " + onStrikeBatsman.getPlayerName() + " is out for "
										+ onStrikeBatsman.getRunsScored() + "(" + onStrikeBatsman.getBallsPlayed()
										+ "). Next to come in is " + nextBatsmanIn.getPlayerName() + "\n");
						onFieldBatsmen.put("striker", nextBatsmanIn);
					}
					break;
				}
			}
		}
		return runsScored;
	}

	private Map<String, Player> rotateStrike(Map<String, Player> onFieldBatsmen) {
		Player tempStriker = onFieldBatsmen.get("striker");
		onFieldBatsmen.put("striker", onFieldBatsmen.get("non-striker"));
		onFieldBatsmen.put("non-striker", tempStriker);
		return onFieldBatsmen;
	}
}
