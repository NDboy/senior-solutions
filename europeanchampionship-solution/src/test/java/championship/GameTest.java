package championship;


import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {



    @Test
    void testGetTheWinner() {
        Game game1 = new Game("Hungary", "Germany", 2, 2);
        Game game2 = new Game("Hungary", "Portugal", 0, 3);
        Game gameDream = new Game("Hungary", "Portugal", 3, 0);

        assertEquals("draw", game1.getTheWinner());
        assertEquals("Portugal", game2.getTheWinner());
        assertEquals("Hungary", gameDream.getTheWinner());
    }
}