package my.wypozyczalnia.factories;

import java.math.BigDecimal;
import my.wypozyczalnia.model.Cars;

public class CarFactory extends WehicleFactory {
    public Cars create(String marka, String model, BigDecimal cena, String dostepnosc) {
        return new Cars(marka, model, cena, dostepnosc);
    }
}
