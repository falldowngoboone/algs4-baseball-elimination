
/******************************************************************************
 *  Compilation:  javac BaseballElimination.java
 * 
 *  Figures out what teams have been mathematically eliminated
 ******************************************************************************/
import edu.princeton.cs.algs4.StdOut;
import java.util.HashMap;
import java.util.Map;
import edu.princeton.cs.algs4.In;

public class BaseballElimination {
  private final int numberOfTeams;
  private Map<String, Integer> teams;
  private int[] wins;
  private int[] losses;
  private int[] remaining;
  private int[][] against;

  // create a baseball division from given filename in format specified below
  public BaseballElimination(String filename) {
    // handle non-trivial reasons
    In file = new In(filename);
    numberOfTeams = Integer.parseInt(file.readLine());
    teams = new HashMap<String, Integer>(numberOfTeams);
    wins = new int[numberOfTeams];
    losses = new int[numberOfTeams];
    remaining = new int[numberOfTeams];
    against = new int[numberOfTeams][numberOfTeams];
    for (int i = 0; i < numberOfTeams; i++) {
      teams.put(file.readString(), i);
      wins[i] = file.readInt();
      losses[i] = file.readInt();
      remaining[i] = file.readInt();
      for (int j = 0; j < numberOfTeams; j++) {
        if (j == i) continue;
        against[i][j] = file.readInt();
      }
    }
  }

  // number of teams
  public int numberOfTeams() {
    return numberOfTeams;
  }

  // all teams
  public Iterable<String> teams() {
    return teams.keySet();
  }

  // number of wins for given team
  public int wins(String team) {
    if (!teamIsValid(team)) throw new IllegalArgumentException();
    return wins[teams.get(team)];
  }
  
  // number of losses for given team
  public int losses(String team) {
    if (!teamIsValid(team)) throw new IllegalArgumentException();
    return losses[teams.get(team)];
  }
  
  // number of remaining games for given team
  public int remaining(String team) {
    if (!teamIsValid(team)) throw new IllegalArgumentException();
    return remaining[teams.get(team)];
  }
  
  // number of remaining games between team1 and team2
  public int against(String team1, String team2) {
    if (!teamIsValid(team1) || !teamIsValid(team2)) throw new IllegalArgumentException();
    return against[teams.get(team1)][teams.get(team2)];
  }

  // is given team eliminated?
  public boolean isEliminated(String team) {
    return false;
  }

  // subset R of teams that eliminates given team; null if not eliminated
  public Iterable<String> certificateOfElimination(String team) {
    return null;
  }

  private boolean teamIsValid(String possibleTeam) {
    return teams.containsKey(possibleTeam);
  }

  public static void main(String[] args) {
    BaseballElimination division = new BaseballElimination(args[0]);
    for (String team : division.teams()) {
      if (division.isEliminated(team)) {
        StdOut.print(team + " is eliminated by the subset R = { ");
        for (String t : division.certificateOfElimination(team)) {
          StdOut.print(t + " ");
        }
        StdOut.println("}");
      } else {
        StdOut.println(team + " is not eliminated");
      }
    }
  }
}