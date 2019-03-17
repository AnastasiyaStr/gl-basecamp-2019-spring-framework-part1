package home.ua.service.Impl;
import lombok.Getter;
import lombok.Setter;
import home.ua.service.NumberGenerator;
import org.springframework.beans.factory.annotation.Value;
import java.util.Random;
@Getter
@Setter
public class NumberGeneratorImpl implements NumberGenerator {
    private final Random random = new Random();
    @Value("${guess.max}")
    private int maxNumber;
    @Value("${guess.min}")
    private int minNumber;
    @Override
    public int next() {
        return  random.nextInt(maxNumber);
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }
}
