package my.wypozyczalnia.factories;

import java.math.BigDecimal;
import my.wypozyczalnia.model.Cars;
import my.wypozyczalnia.model.Motors;
import my.wypozyczalnia.model.Quads;
import my.wypozyczalnia.model.Wehicle;

public class WehicleFactory {
    public Wehicle create(String type, String marka, String model, BigDecimal cena, String dostepnosc) {
        if (type == "Car") {
            CarFactory cf = new CarFactory();
            return cf.create(marka, model, cena, dostepnosc);
        } else if (type == "Motor") {
            MotorFactory mf = new MotorFactory();
            return mf.create(marka, model, cena, dostepnosc);
        } else {
            QuadFactory qf = new QuadFactory();
            return qf.create(marka, model, cena, dostepnosc);
        }
    }
}
