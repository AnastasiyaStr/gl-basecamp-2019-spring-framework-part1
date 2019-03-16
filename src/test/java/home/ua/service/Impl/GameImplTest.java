package home.ua.service.Impl;

import home.ua.GuessConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class GameImplTest {
private  GameImpl game;
    @Before
    public void setUpt(){
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(GuessConfig.class);
         game = context.getBean("game", GameImpl.class);
    }

    @Test
    public void check() {
        game.setNumber(14);
        assertEquals(1,game.check(15));
        assertEquals(-1,game.check(13));
        assertEquals(0,game.check(155));
    }

    @Test
    public void isGameWon() {
        game.setNumber(14);
        assertTrue(game.isGameWon(14));
    }

    @Test
    public void isGameLost() {
        game.setRemainingGuesses(-1);
        assertTrue(game.isGameLost(14));
    }
}