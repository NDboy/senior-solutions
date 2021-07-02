package championship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class GameServiceTest {

    GameService gameService;

    @BeforeEach
    void init() {
        GameRepository repository = new GameRepository();
        repository.addGame(new Game("Turkey", "Italy", 2, 3));
        repository.addGame(new Game("Switzerland", "Slovakia", 4, 1));
        repository.addGame(new Game("Sweden", "Wales", 1, 7));
        repository.addGame(new Game("Slovakia", "Italy", 4, 3));
        repository.addGame(new Game("Poland", "Turkey", 1, 1));
        repository.addGame(new Game("Netherlands", "Germany", 5, 2));
        gameService = new GameService(repository);

    }

    @Test
    void testGetTheBiggestDifference() {
        assertEquals("Wales", gameService.getTheBiggestDifference().getSecondCountry());
    }

    @ParameterizedTest
    @MethodSource("createArguments")
    void testGetTotalGoalsByCountryParameterized(String country, int goals) {
        assertEquals(goals, gameService.getTotalGoalsByCountry(country));
    }

    static Stream<Arguments> createArguments() {
        return Stream.of(
                arguments("Turkey", 3),
                arguments("Italy", 6),
                arguments("Slovakia", 5),
                arguments("Wales", 7)
        );
    }


    @Test
    void testGetTheBestCountry() {
        assertEquals("Wales", gameService.getTheBestCountry());
    }
}