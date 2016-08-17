package my.wypozyczalnia.factories;

import java.math.BigDecimal;
import my.wypozyczalnia.model.Motors;

public class MotorFactory extends WehicleFactory {
    public Motors create(String marka, String model, BigDecimal cena, String dostepnosc) {
        return new Motors(marka, model, cena, dostepnosc);
    }
}