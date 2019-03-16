package home.ua;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import home.ua.service.Game;
import home.ua.service.NumberGenerator;


public class AnnotationDemoApp {

    private static final Logger log = LoggerFactory.getLogger(AnnotationDemoApp.class);

    private static final String CONFIG_LOCATION = "beans.xml";

    public static void main(String[] args) {

        ConfigurableApplicationContext context
                = new ClassPathXmlApplicationContext(CONFIG_LOCATION);

        NumberGenerator numberGenerator
                = context.getBean("NumberGenerator", NumberGenerator.class);
        int guess = numberGenerator.next();
        log.info("My guess = {}", guess);

        // get bean
        Game game = context.getBean("Game",Game.class);
        game.setGuess(guess);

        log.info("Result = {}", game.isGameWon() ? "Win" : "Lose");

        // close context
        context.close();
    }
}
