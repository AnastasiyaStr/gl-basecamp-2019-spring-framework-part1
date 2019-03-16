package home.ua;

import home.ua.service.Game;
import home.ua.service.Impl.GameImpl;
import home.ua.service.Impl.NumberGeneratorImpl;
import home.ua.service.NumberGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan("home.ua.service")
public class GuessConfig {

   @Bean
   public NumberGenerator numberGenerator(){
       return new NumberGeneratorImpl();
   }



@Bean
    public Game game(){
    return new GameImpl(numberGenerator());
}
}
