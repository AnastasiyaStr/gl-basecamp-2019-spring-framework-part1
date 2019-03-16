package home.ua;

import home.ua.service.Game;
import home.ua.service.Impl.GameImpl;
import home.ua.service.NumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class JavaConfigDemoApp {
    private static final Logger log = LoggerFactory.getLogger(AnnotationDemoApp.class);

    private static final String CONFIG_LOCATION = "beans.xml";

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(GuessConfig.class);

    //    NumberGenerator numberGenerator = context.getBean("numberGenerator", NumberGenerator.class);
     /*   int guess = numberGenerator.next();
        log.info("My guess = {}", guess);*/

        // get bean
        GameImpl game = context.getBean("game", GameImpl.class);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number:");
        int guess = scanner.nextInt();

        while(true) {
           int i =  game.check(guess);
           switch (i) {
               case -1:
                   System.out.println("Number is bigger!!!");
                   break;
               case 1:
                   System.out.println("Number is less!!!");
                   break;
               case 0:
                   System.out.println("Number should be withing range!!!");
                   break;
           }
            if (game.isGameWon(guess)) {
                System.out.println("You won!!");
                break;
            }
            if (game.isGameLost(guess)) {
                System.out.println("You lost");
                break;
            }
           guess = scanner.nextInt();
        }
        // close context
        context.close();
    }


    }

