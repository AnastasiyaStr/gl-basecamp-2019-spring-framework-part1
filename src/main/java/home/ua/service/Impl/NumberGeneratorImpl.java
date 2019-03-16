package home.ua.service.Impl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import home.ua.service.NumberGenerator;

import java.util.Random;

@Component("NumberGenerator")
@Getter
@Setter
public class NumberGeneratorImpl implements NumberGenerator {

    private final Random random = new Random();
    private int maxNumber = 100;

    @Override
    public int next() {
        return random.nextInt(maxNumber);
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }
}
