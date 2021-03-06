<h1 align="center">Welcome to my Java Generics Challenge Solution 👋</h1>
<p>
</p>

> My solution to the Generics challenge in Section 10 of [Tim Buchalka's Java Masterclass](https://www.udemy.com/share/101Wdq2@Pm1KfWJjTlQGc0FHBnJnfhRuSn1h/) on Udemy

## The challenge

Create a generic class to implement a league table for a sport.

This class should allow teams to be added to the list, and store a list of teams that belong to the league.

Your class should have a method to print out the teams in order, with the team at the top of the league printed first

Only teams with the same type should be added to any particular instance of the league class - the program should fail to compile if an attempt is made to add an incompatible team.

## My solution

### Defining our `Sport` class

The first step was to create an abstract `Sport` class that could be used for the generic typing of various future concrete classes.

``` Java

public abstract class Sport {
    private final String name;

    public Sport(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

```

We can now extend this class to create concrete sports classes (Football, Basketball, etc.)

``` Java

public class Football extends Sport {
    public Football() {
        super("Football");
    }
}

public class Basketball extends Sport {
    public Basketball() {
        super("Basketball");
    }
}

```

### Defining our `Team` class

Now that we have our sports classes, we can use them when we instantiate our Team class.  But first, we have to create it.

``` Java

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

```

Things to note:

- `Team` is a generic class and must be instantiated with a Type (`T`) that extends `Sport`.  Attempting to create a `Team` with an invalid type will result in a compile-time error.
- We are implementing the `Comparable` interface, which allows us to sort our teams by their ranking.
- We are also overriding the default `toString` method, allowing us to customize how our object looks when printed to the console.

### Defining our `SportsLeague` class

``` Java

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

```

This class is relatively simple.  It stores a list of teams, allows us to add a team to the list, and can print the teams in the order of their ranking.

### Putting it all together

First, lets use the concrete `Sport` classes we created to instantiate respective `SportsLeague` instances.

``` Java

SportsLeague<Football> nfl = new SportsLeague<>("NFL");
SportsLeague<Basketball> nba = new SportsLeague<>("NBA");

```

Next, lets create a few teams.

``` Java

Team<Football> nyg = new Team<>("New York", "Giants");
Team<Football> colts = new Team<>("Indianapolis", "Colts");
Team<Basketball> suns = new Team<>("Phoenix", "Suns");

```

With our teams created, we can now add them to our leagues.

``` Java

nfl.addTeam(nyg);
nfl.addTeam(colts);
nfl.addTeam(suns); // this will throw an error!
nba.addTeam(suns);

```

Now we can record a match between our teams and test our sorting functionality!

``` Java

colts.recordMatch(nyg, 7, 0);
nfl.printTeams();

```

``` plaintext

NFL teams: Indianapolis Colts (01-00-00), New York Giants (00-01-00).

```

## Author

👤 **Jadon Calvert**

- Github: [@calvertjadon](https://github.com/calvertjadon)

## Show your support

Give a ⭐️ if this project helped you!

***
_This README was generated with ❤️ by [readme-md-generator](https://github.com/kefranabg/readme-md-generator)_
