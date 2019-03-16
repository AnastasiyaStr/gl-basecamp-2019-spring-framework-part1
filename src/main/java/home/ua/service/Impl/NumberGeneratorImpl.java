package home.ua.service.Impl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import home.ua.service.NumberGenerator;

import java.util.Random;
import java.util.Scanner;

//@Component("NumberGenerator")
@Getter
@Setter
public class NumberGeneratorImpl implements NumberGenerator {

    private final Random random = new Random();
    private int maxNumber = 100;
    private int minNumber = 0;
    @Override
    public int next() {
      /* Scanner scanner = new Scanner(System.in);
        System.out.println("ENterrrr:");
        return 54;*/
        return  random.nextInt(maxNumber);
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }
}
