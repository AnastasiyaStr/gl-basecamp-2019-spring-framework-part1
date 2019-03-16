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
   // private int smallest=0;
  //  private int biggest;
    private int remainingGuesses;
  //  private boolean validNumberRange = true;

    //@Autowired
    public GameImpl(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }
    @Override
    @PostConstruct
    public void reset() {
          //  smallest = 0;
          //  guess = 51;
            remainingGuesses = guessCount;
           // biggest = numberGenerator.getMaxNumber();
            number = numberGenerator.next();

       // System.out.println("the number is {}" + number);
      /*  while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("ENterrrr:");
            guess=scanner.nextInt();
            check(guess);
            System.out.println("the number is {}" + number);
            if(isGameWon(guess)){
                System.out.println("You won!!");break;}
            if(isGameLost(guess)){
                System.out.println("You lost");break;}
                remainingGuesses--;
        }*/
    }

    @PreDestroy
    public void preDestroy() {
        log.info("in Game preDestroy()");
        //log.info("Result = {}", isGameWon(guess) ? "Win" : "Lose");
    }



    public int check(int guess) {
        if(checkValidNumberRange(guess)) {
            if(guess > number) {
               // System.out.println("Number is less!!!");
                return 1;
            }

            if(guess < number) {
                //System.out.println("Number is bigger!!!");
                return -1;
            }
        }else{
           // System.out.println("Your number should be within range!!!");
            return 0;
        }
        remainingGuesses--;
        return 2;
    }




    public boolean isGameWon(int guess) {
        return guess == number;
    }


    public boolean isGameLost(int guess) {
        return guess!=number&&remainingGuesses<=0;
    }


    private boolean checkValidNumberRange(int guess) {
       return (guess >= numberGenerator.getMinNumber()) && (guess <= numberGenerator.getMaxNumber());
    }


}
