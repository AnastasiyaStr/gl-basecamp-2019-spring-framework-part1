package home.ua;

import home.ua.service.Game;
import home.ua.service.Impl.GameImpl;
import home.ua.service.NumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class JavaConfigDemoApp {
    private static final Logger log = LoggerFactory.getLogger(JavaConfigDemoApp.class);
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(GuessConfig.class);

        // get bean
        GameImpl game = context.getBean("game", GameImpl.class);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, "+game.getName());
        System.out.println("Enter the number:");
        int guess = scanner.nextInt();
        log.info("My guess = {}", guess);
        while(true) {
           int i =  game.check(guess);
           switch (i) {
               case -1:
                   System.out.println("Number you want to guess is bigger!!!");
                   log.info("Guesses left:{}", game.getRemainingGuesses());
                   break;
               case 1:
                   System.out.println("Number you want to guess is less!!!");
                   log.info("Guesses left:{}", game.getRemainingGuesses());
                   break;
               case 0:
                   System.out.println("Number should be withing range!!!");
                   log.info("Guesses left:{}", game.getRemainingGuesses());
                   break;
           }

            if (game.isGameWon(guess)) {
                System.out.println("You won!!");
                break;
            }
            if (game.isGameLost(guess)) {
                System.out.println("You lost");
                log.info("Number:{}", game.getNumber());
                break;
            }
           guess = scanner.nextInt();
            log.info("My guess = {}", guess);
        }
        // close context
        context.close();
    }


    }

