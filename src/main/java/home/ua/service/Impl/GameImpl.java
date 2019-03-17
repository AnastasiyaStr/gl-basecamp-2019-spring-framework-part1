package home.ua.service.Impl;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import home.ua.service.Game;
import home.ua.service.NumberGenerator;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Getter
@Setter
public class GameImpl implements Game {
    @Value("${guess.name}")
    private String name;
    private static final Logger log = LoggerFactory.getLogger(GameImpl.class);
    private final NumberGenerator numberGenerator;
    @Value("${guess.count}")
    private int guessCount;
    private int number;
    private int guess;
    private int remainingGuesses;

    public GameImpl(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }
    @Override
    @PostConstruct
    public void reset() {
            remainingGuesses = guessCount;
            number = numberGenerator.next();
    }

    @PreDestroy
    public void preDestroy() {
        log.info("in Game preDestroy()");

    }



    public int check(int guess) {
        if(checkValidNumberRange(guess)) {
            if(guess > number) {
                remainingGuesses--;
                return 1;
            }

            if(guess < number) {
                remainingGuesses--;
                return -1;
            }
        }else{
            remainingGuesses--;
            return 0;
        }

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
