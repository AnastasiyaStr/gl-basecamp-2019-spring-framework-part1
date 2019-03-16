package home.ua;

import home.ua.service.Game;
import home.ua.service.NumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JavaConfigDemoApp {
    private static final Logger log = LoggerFactory.getLogger(AnnotationDemoApp.class);

    private static final String CONFIG_LOCATION = "beans.xml";

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(GuessConfig.class);

        NumberGenerator numberGenerator
                = context.getBean("numberGenerator", NumberGenerator.class);
        int guess = numberGenerator.next();
        log.info("My guess = {}", guess);

        // get bean
        Game game = context.getBean("game",Game.class);
        game.setGuess(guess);

        log.info("Result = {}", game.isGameWon() ? "Win" : "Lose");

        // close context
        context.close();
    }
}
