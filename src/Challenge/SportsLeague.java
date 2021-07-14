package Challenge;

import Challenge.Sports.Sport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class SportsLeague<T extends Sport> {
    private final String name;
    private final ArrayList<Team<T>> teams = new ArrayList<>();

    public SportsLeague(String name) {
        this.name = name;
    }

    public void addTeam(Team<T> newTeam) {
        if (!teams.contains(newTeam)) {
            teams.add(newTeam);
        } else {
            System.out.println(newTeam.getName() + " already belongs to the league '" + this.getName() + "'");
        }
    }

    public void printTeams() {
        Collections.sort(teams);
        System.out.printf("%s teams: %s.%n", this.getName(), this.teams.stream().map(Team<T>::toString).collect(Collectors.joining(", ")));
    }

    public String getName() {
        return name;
    }
}
