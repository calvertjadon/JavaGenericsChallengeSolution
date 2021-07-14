package Challenge;

import Challenge.Sports.Sport;

public class Team<T extends Sport> implements Comparable<Team<T>> {
    private final String city;
    private final String name;
    public int wins;
    public int losses;
    public int ties;

    public Team(String city, String name) {
        this.city = city;
        this.name = name;
    }

    public int getRanking() {
        return (this.wins*2) + this.ties;
    }

    public void recordMatch(Team<T> opponent, int ourScore, int theirScore) {
        if (ourScore > theirScore) {
            wins++;
        } else if (ourScore < theirScore) {
            losses++;
        } else {
            ties++;
        }

        if (opponent != null) {
            opponent.recordMatch(null, theirScore, ourScore);
        }
    }

    @Override
    public int compareTo(Team team) {
        if (this.getRanking() > team.getRanking()) {
            return -1;
        }
        if (this.getRanking() < team.getRanking()) {
            return 1;
        }
        return 0;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%02d-%02d-%02d)", this.getCity(), this.getName(), this.wins, this.losses, this.ties);
    }
}
