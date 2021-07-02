package championship;

import java.util.*;
import java.util.stream.Collectors;

public class GameService {

    private GameRepository repository;

    public GameService(GameRepository repository) {
        this.repository = repository;
    }

    public Game getTheBiggestDifference() {
        return repository.getGames()
                .stream()
                .max(Comparator.comparing(g -> Math.abs(g.getFirstCountryScore() - g.getSecondCountryScore())))
                .orElseThrow(() -> new IllegalArgumentException("No game found"));
    }

    public int getTotalGoalsByCountry(String country) {
        return repository.getGames()
                .stream()
                .filter(g -> g.getFirstCountry().equals(country) || g.getSecondCountry().equals(country))
                .mapToInt(game -> getGoalsByCountry(game, country))
                .sum();
    }

    private int getGoalsByCountry(Game game, String country) {
        if(country.equals(game.getFirstCountry())) {
            return game.getFirstCountryScore();
        } else if (country.equals(game.getSecondCountry())) {
            return game.getSecondCountryScore();
        }
        return 0;
    }

    public String getTheBestCountry() {
        return repository.getGames()
                .stream()
                .map(this::processGames)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Score::getCountry, Collectors.summingInt(Score::getGoals)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null)
                .getKey();

    }

//    private Map<String, Integer> putGamesToMap(Map<String, Integer> map) {
//
//    }

    private List<Score> processGames(Game game) {
        List<Score> scores = new ArrayList<>();
        Score score1 = new Score(game.getFirstCountry(), game.getFirstCountryScore());
        Score score2 = new Score(game.getSecondCountry(), game.getSecondCountryScore());
        scores.add(score1);
        scores.add(score2);
        return scores;
    }

    public static class Score {

        private String country;
        private int goals;

        public Score(String country, int goals) {
            this.country = country;
            this.goals = goals;
        }

        public String getCountry() {
            return country;
        }

        public int getGoals() {
            return goals;
        }
    }


}


//        + Határozd meg a legnagyobb gólkülönbséggel véget ért mérkőzést
//        + Határozd meg hogy egy paraméterül kapott ország hány gólt rúgott eddig
//        + Határozd meg az eddig legtöbb gólt rúgó országot

