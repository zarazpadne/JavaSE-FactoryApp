
package my.wypozyczalnia.factories;

import java.math.BigDecimal;
import my.wypozyczalnia.model.Quads;

public class QuadFactory extends WehicleFactory {
    public Quads create(String marka, String model, BigDecimal cena, String dostepnosc) {
        return new Quads(marka, model, cena, dostepnosc);
    }
}
