package my.wypozyczalnia.model;

import java.math.BigDecimal;

public interface Wehicle {
    public int buy();
    public void sell();
    public void lend();
    public void return_wehicle();
    public String getModel();
    public String getMarka();
    public void setDostepnosc(String dostepnosc);
    public BigDecimal getCena();
    public String getDostepnosc();
}
