package championship;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GameRepository {

    private List<Game> games = new ArrayList<>();

    public List<Game> getGames() {
        return games;
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public void loadFileToList(String pathString) {
        try (BufferedReader br = Files.newBufferedReader(Path.of(pathString))){
            br.lines()
                    .map(this::processLine)
                    .forEach(a -> games.add(a));
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }
    }

    private Game processLine(String lineString) {
        String firstCountry = lineString.split(";")[0];
        String secondCountry = lineString.split(";")[1];
        int firstCountryScore = Integer.parseInt(lineString.split(";")[2]);
        int secondCountryScore = Integer.parseInt(lineString.split(";")[3]);
        return new Game(firstCountry, secondCountry,firstCountryScore, secondCountryScore);
    }
}


