package OurGenericsClass;

import java.util.ArrayList;

public class Team<T extends Player> implements Comparable<Team<T>> {
    private String name;
    int played = 0;
    int won = 0;
    int lost = 0;
    int tied = 0;

    private ArrayList<T> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Team<T> team) {
        if (this.getRanking() > team.getRanking()) {
            return -1;
        }
        if (this.getRanking() < team.getRanking()) {
            return 1;
        }
        return 0;
    }

    public String getName() {
        return name;
    }

    public boolean addPlayer(T newPlayer) {
        if (members.contains(newPlayer)) {
            System.out.println(newPlayer.getName() + " is already on this team (" + this.getName() + ")");
            return false;
        } else {
            members.add(newPlayer);
            System.out.println(newPlayer.getName() + " picked for team " + this.getName());
            return true;
        }
    }

    public int getNumPlayers() {
        return this.members.size();
    }

    public void addMatchResult(Team<T> opponent, int ourScore, int theirScore) {
        String message = "";
        if (ourScore > theirScore) {
            won++;
            message = " beat ";
        } else if (ourScore == theirScore) {
            tied++;
            message = " tied with ";
        } else {
            lost++;
            message = " lost to ";
        }

        if (opponent != null) {
            System.out.println(this.getName() + message + opponent.getName());
            opponent.addMatchResult(null, theirScore, ourScore);
        }
    }

    public int getRanking() {
        return (won * 2) + tied;
    }
}
