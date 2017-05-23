import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.List;

public class Compare {

	public static void main(String... arg) {
		// String class implements Comparable.
		Arrays.sort(playerNamesArray);
		System.out.println("player: " + Arrays.toString(playerNamesArray));

		// If Player class does not implement Comparable then need to pass
		// comparator.
		Set<Player> playerSet1 = new TreeSet<Player>(Player.NAME_COMPARATOR);
		playerSet1.addAll(playerList);
		System.out.println("playerSet1: " + playerSet1);

		// Will sort according to the comparable implementation.
		Set<Player> playerSet = new TreeSet<Player>(Arrays.asList(playersArray));
		System.out.println("playerSet: " + playerSet);

		Arrays.sort(playersArray);
		System.out.println("player: " + Arrays.toString(playerNamesArray));

		playerList.sort(Player.NAME_COMPARATOR);
		Collections.sort(playerList);
		Collections.sort(playerList, Player.ID_THEN_NAME_COMPARATOR);
		Collections.sort(playerList, (p1, p2) -> p1.getPlayerId().compareTo(p2.getPlayerId()));
		Collections.sort(playerList, (p1, p2) -> {
			if (p1.getName().compareTo(p2.getName()) == 0) {
				return p1.getPlayerId().compareTo(p2.getPlayerId());
			} else {
				return p1.getName().compareTo(p2.getName());
			}
		});
		// Inline inner class
		Collections.sort(playerList, new Comparator<Player>() {
			@Override
			public int compare(Player o1, Player o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		

		System.out.println("ID_THEN_NAME_COMPARATOR:" + playerList);

		// Creates and sorts a stream (does not sort the original list)
		playerList = playerList.stream().sorted(Comparator.comparing(Player::getRank).thenComparing(Player::getName))
				.collect(Collectors.toList());
		System.out.println("Using streams(): " + playerList);

		// Sorts the original list.
		playerList.sort(Comparator.comparingInt(Player::getRank).thenComparing(Player::getName));
		System.out.println(playerList);

		// Sorting using Lambda. Sorts the original list.
		playerList.sort((p1, p2) -> {
			if (p1.getName().compareTo(p2.getName()) == 0) {
				return p1.getPlayerId().compareTo(p2.getPlayerId());
			} else {
				return p1.getName().compareTo(p2.getName());
			}
		});

	}

	static String[] playerNamesArray = new String[] { "Puppey", "Notail", "Jerax", "Arteezy", "Hao", "Xiao8",
			"Burning" };

	static Player[] playersArray = new Player[7];

	static List<Player> playerList;

	static {
		playersArray[0] = new Player(1L, "Puppey", 322);
		playersArray[3] = new Player(2L, "Notail", 422);
		playersArray[2] = new Player(3L, "Jerax", 222);
		playersArray[5] = new Player(4L, "Arteezy", 122);
		playersArray[6] = new Player(5L, "Hao", 522);
		playersArray[1] = new Player(6L, "Xiao8", 622);
		playersArray[4] = new Player(3L, "Burning", 722);

		playerList = new ArrayList<Player>((Arrays.asList(playersArray))); //
	}

}

/**
 * Remember to override equals and hashCode to be consistent with compareTo
 * method.
 */
class Player implements Comparable<Player> {

	private Long playerId;
	private String name;
	private int rank;

	public Player(Long playerId, String name, int rank) {
		super();
		this.playerId = playerId;
		this.name = name;
		this.rank = rank;
	}

	public static final Comparator<Player> ID_COMPARATOR = new Comparator<Player>() {
		@Override
		public int compare(Player player1, Player player2) {
			// for descending order
			// return player2.getPlayerId().compareTo(player1.getPlayerId());
			// for ascending order
			return player1.getPlayerId().compareTo(player2.getPlayerId());
		}
	};

	public static final Comparator<Player> ID_THEN_NAME_COMPARATOR = new Comparator<Player>() {
		@Override
		public int compare(Player player1, Player player2) {
			int i = player1.getPlayerId().compareTo(player2.getPlayerId());
			if (i != 0)
				return i;

			return player1.getName().compareToIgnoreCase(player2.getName());
		}
	};

	public static final Comparator<Player> NAME_COMPARATOR = new Comparator<Player>() {
		@Override
		public int compare(Player player1, Player player2) {
			// for descending order
			// return player2.getName().compareToIgnoreCase(player1.getName());
			// for ascending order
			return player1.getName().compareToIgnoreCase(player2.getName());
		}
	};

	@Override
	public int compareTo(Player o) {
		// Chronological
		return this.getPlayerId().compareTo(o.getPlayerId());
		// Reverse Chronological
		// return o.getPlayerId().compareTo(this.getPlayerId());
	}

	/*
	 * @Override public int compareTo(Player o) { // Chronological return
	 * this.getName().compareToIgnoreCase(o.getName()); // Reverse Chronological
	 * // return o.getName().compareToIgnoreCase(this.getName()); }
	 */

	/*
	 * @Override public int compareTo(Player o) { // Chronological return
	 * Integer.compare(this.getRank(), o.getRank()); // Reverse Chronological
	 * //return Integer.compare(o.getRank(), this.getRank()); }
	 */

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Player [playerId=").append(playerId).append(", name=").append(name).append(", rank=")
				.append(rank).append("]");
		return builder.toString();
	}
}
