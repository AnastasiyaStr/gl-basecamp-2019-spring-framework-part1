package home.ua.service.Impl;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import home.ua.service.Game;
import home.ua.service.NumberGenerator;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Scanner;

//@Component("Game")
@Getter
@Setter
public class GameImpl implements Game {
    private static final Logger log = LoggerFactory.getLogger(GameImpl.class);

    private final NumberGenerator numberGenerator;


    private int guessCount = 10;
    private int number;
    private int guess;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    //@Autowired
    public GameImpl(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    @PostConstruct
    @Override
    public void reset() {

            smallest = 0;
            guess = 51;
            remainingGuesses = guessCount;
            biggest = numberGenerator.getMaxNumber();
            number = numberGenerator.next();
        while(true) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("ENterrrr:");
            guess=scanner.nextInt();
            check();
            System.out.println("the number is {}" + number);
            if(isGameWon()){
                System.out.println("You won!!");break;}
            if(guess!=number&&remainingGuesses<=0){
                System.out.println("You lost");break;}
                remainingGuesses--;
        }
    }

    @PreDestroy
    public void preDestroy() {
        log.info("in Game preDestroy()");
        log.info("Result = {}", isGameWon() ? "Win" : "Lose");
    }


   @Override
    public void check() {

        checkValidNumberRange();

        if(validNumberRange) {
            if(guess > number) {
                System.out.println("Number is less!!!");
            }

            if(guess < number) {
                System.out.println("Number is bigger!!!");
            }
        }

        remainingGuesses--;
    }



    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }


    private void checkValidNumberRange() {
        validNumberRange = (guess >= smallest) && (guess <= biggest);
    }


}
