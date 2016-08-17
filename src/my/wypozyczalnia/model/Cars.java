/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.wypozyczalnia.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author k
 */
@Entity
@Table(name = "CARS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cars.findAll", query = "SELECT c FROM Cars c"),
    @NamedQuery(name = "Cars.findByIdCar", query = "SELECT c FROM Cars c WHERE c.idCar = :idCar"),
    @NamedQuery(name = "Cars.findByMarka", query = "SELECT c FROM Cars c WHERE c.marka = :marka"),
    @NamedQuery(name = "Cars.findByModel", query = "SELECT c FROM Cars c WHERE c.model = :model"),
    @NamedQuery(name = "Cars.findByCena", query = "SELECT c FROM Cars c WHERE c.cena = :cena"),
    @NamedQuery(name = "Cars.findByDostepnosc", query = "SELECT c FROM Cars c WHERE c.dostepnosc = :dostepnosc")})
public class Cars implements Serializable, Wehicle{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CAR")
    private Integer idCar;
    @Basic(optional = false)
    @Column(name = "MARKA")
    private String marka;
    @Basic(optional = false)
    @Column(name = "MODEL")
    private String model;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "CENA")
    private BigDecimal cena;
    @Basic(optional = false)
    @Column(name = "DOSTEPNOSC")
    private String dostepnosc;
    private static String dbURL = "jdbc:derby://localhost:1527/factorymethodDB";

    public Cars() {
    }

    public Cars(int id) {
        try {
            Connection connection = DriverManager.getConnection(dbURL,"APP","APP"); 
            Statement s = connection.createStatement();
            String sql = "SELECT marka, model, cena, dostepnosc FROM CARS WHERE id_car = " + id;
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                this.model = rs.getString(1);
                this.marka = rs.getString(2);
                this.cena = rs.getBigDecimal(3);
                this.dostepnosc = rs.getString(4);
                this.idCar = id;
            } 
            s .close();
        } catch (Exception except) {
            except.printStackTrace();
        }
    }

    public Cars(String marka, String model, BigDecimal cena, String dostepnosc) {
        this.marka = marka;
        this.model = model;
        this.cena = cena;
        this.dostepnosc = dostepnosc;
        this.idCar = buy();
    }
    
    public int buy(){
        int idCar = -1;
        try {
            Connection connection = DriverManager.getConnection(dbURL, "APP", "APP"); 
            Statement s = connection.createStatement();
            String sql = "INSERT INTO Cars(marka, model, cena, dostepnosc) VALUES('"+this.model+"', '" + this.marka +  "', " + this.cena + ", '" + this.dostepnosc + "')";
            s.executeUpdate(sql, 1);
            ResultSet rs = s.getGeneratedKeys();
            if (rs.next()) {
                idCar = rs.getInt(1);
            }
        } catch (Exception except) {
            except.printStackTrace();
        }
        return idCar;
    }
    
    public void sell() {
        try {
            Connection connection = DriverManager.getConnection(dbURL,"APP","APP"); 
            Statement s = connection.createStatement();
            String sql = "DELETE FROM CARS WHERE ID_CAR="+this.idCar;
            s.execute(sql);
        } catch (Exception except) {
            except.printStackTrace();
        }
    }
    
    public void lend() {
        try {
            Connection connection = DriverManager.getConnection(dbURL,"APP","APP"); 
            Statement s = connection.createStatement();
            String sql = "UPDATE CARS SET DOSTEPNOSC = 'false' WHERE id_car = " + this.idCar;
            this.dostepnosc = "false";
            s.execute(sql);
        } catch (Exception except) {
            except.printStackTrace();
        }
    }
    
    public void return_wehicle() {
        try {
            Connection connection = DriverManager.getConnection(dbURL,"APP","APP"); 
            Statement s = connection.createStatement();
            String sql = "UPDATE CARS SET DOSTEPNOSC = 'true' WHERE id_car = " + this.idCar;
            this.dostepnosc = "true";
            s.execute(sql);
        } catch (Exception except) {
            except.printStackTrace();
        }
    }

    public Integer getIdCar() {
        return idCar;
    }

    public void setIdCar(Integer idCar) {
        this.idCar = idCar;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public String getDostepnosc() {
        return dostepnosc;
    }

    public void setDostepnosc(String dostepnosc) {
        this.dostepnosc = dostepnosc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCar != null ? idCar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cars)) {
            return false;
        }
        Cars other = (Cars) object;
        if ((this.idCar == null && other.idCar != null) || (this.idCar != null && !this.idCar.equals(other.idCar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Marka: " + this.marka + ", Model: " + this.model + ", Cena: " + this.cena + ", Dostepnosc: " + this.dostepnosc;
    }
    
}
