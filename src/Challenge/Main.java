package Challenge;

import Challenge.Sports.Basketball;
import Challenge.Sports.Football;

public class Main {
    public static void main(String[] args) {
        // ArrayList<Team> teams;
        // Collections.sort(teams);

        // Create a generic class to implement a league table for a sport
        //
        // this class should allow teams to be added to the list, and store
        // a list of teams that belong to the league.
        //
        // your class should have a method to print out the teams in order,
        // with the team at the top of the league printed first
        //
        // only teams with the same type should be added to any particular
        // instance of the league class - the program should fail to compile
        // if an attempt is made to add an incompatible team.

        SportsLeague<Football> nfl = new SportsLeague<>("NFL");
        SportsLeague<Basketball> nba = new SportsLeague<>("NBA");

        Team<Football> nyg = new Team<>("New York", "Giants");
        Team<Football> colts = new Team<>("Indianapolis", "Colts");
        Team<Basketball> suns = new Team<>("Phoenix", "Suns");

        nfl.addTeam(nyg);
        nfl.addTeam(colts);
        nba.addTeam(suns);

        colts.recordMatch(nyg, 7, 0);

        nfl.printTeams();
        nba.printTeams();
    }
}
