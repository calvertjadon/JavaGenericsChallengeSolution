package OurGenericsClass;

public class Main {
    public static void main(String[] args) {
        FootballPlayer joe = new FootballPlayer("Joe");
        BaseballPlayer pat = new BaseballPlayer("Pat");
        SoccerPlayer beckham = new SoccerPlayer("Beckham");

        Team<FootballPlayer> adelaideCrows = new Team<>("Adelaide Crows");
        adelaideCrows.addPlayer(joe);
//        adelaideCrows.addPlayer(pat);
//        adelaideCrows.addPlayer(beckham);

        System.out.println(adelaideCrows.getNumPlayers());

        Team<BaseballPlayer> baseballTeam;
        baseballTeam = new Team<>("Chicago Cubs");
        baseballTeam.addPlayer(pat);
        System.out.println(baseballTeam.getNumPlayers());

//        Team<String> brokenTeam = new Team<>("this won't work");
//        brokenTeam.addPlayer("sdfasd");

        Team<SoccerPlayer> soccerTeam = new Team<>("USA");
        soccerTeam.addPlayer(beckham);

        Team<FootballPlayer> melbourne = new Team<>("Melbourne");
        FootballPlayer banks = new FootballPlayer("Gordon");
        melbourne.addPlayer(banks);

        Team<FootballPlayer> hawthorn = new Team<>("Hawthorn");
        Team<FootballPlayer> fremantle = new Team<>("Fremantle");

        hawthorn.addMatchResult(fremantle, 1, 0);
        hawthorn.addMatchResult(adelaideCrows, 3, 3);

        adelaideCrows.addMatchResult(fremantle, 2, 4);
//        adelaideCrows.addMatchResult(baseballTeam, 1, 1);

        System.out.println("Rankings: ");
        System.out.println(adelaideCrows.getName() + ": " + adelaideCrows.getRanking());
        System.out.println(melbourne.getName() + ": " + melbourne.getRanking());
        System.out.println(fremantle.getName() + ": " + fremantle.getRanking());
        System.out.println(hawthorn.getName() + ": " + hawthorn.getRanking());

        System.out.println(adelaideCrows.compareTo(melbourne));
        System.out.println(adelaideCrows.compareTo(hawthorn));

    }
}
