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
@Table(name = "MOTORS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Motors.findAll", query = "SELECT m FROM Motors m"),
    @NamedQuery(name = "Motors.findByIdMotor", query = "SELECT m FROM Motors m WHERE m.idMotor = :idMotor"),
    @NamedQuery(name = "Motors.findByMarka", query = "SELECT m FROM Motors m WHERE m.marka = :marka"),
    @NamedQuery(name = "Motors.findByModel", query = "SELECT m FROM Motors m WHERE m.model = :model"),
    @NamedQuery(name = "Motors.findByCena", query = "SELECT m FROM Motors m WHERE m.cena = :cena"),
    @NamedQuery(name = "Motors.findByDostepnosc", query = "SELECT m FROM Motors m WHERE m.dostepnosc = :dostepnosc")})
public class Motors implements Serializable, Wehicle {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MOTOR")
    private Integer idMotor;
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

    public Motors() {
    }

    public Motors(int id) {
        try {
            Connection connection = DriverManager.getConnection(dbURL,"APP","APP"); 
            Statement s = connection.createStatement();
            String sql = "SELECT marka, model, cena, dostepnosc FROM MOTORS WHERE id_motor = " + id;
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                this.model = rs.getString(1);
                this.marka = rs.getString(2);
                this.cena = rs.getBigDecimal(3);
                this.dostepnosc = rs.getString(4);
                this.idMotor = id;
            } 
            s .close();
        } catch (Exception except) {
            except.printStackTrace();
        }
    }

    public Motors(String marka, String model, BigDecimal cena, String dostepnosc) {
        this.marka = marka;
        this.model = model;
        this.cena = cena;
        this.dostepnosc = dostepnosc;
        this.idMotor = buy();
    }
    
    public int buy(){
        int idMotor = -1;
        try {
            Connection connection = DriverManager.getConnection(dbURL,"APP","APP"); 
            Statement s = connection.createStatement();
            String sql = "INSERT INTO Motors(marka, model, cena, dostepnosc) VALUES('"+this.model+"', '" + this.marka +  "', " + this.cena + ", '" + this.dostepnosc + "')";
            s.executeUpdate(sql, 1);
            ResultSet rs = s.getGeneratedKeys();
            if (rs.next()) {
                idMotor = rs.getInt(1);
            }
        } catch (Exception except) {
            except.printStackTrace();
        }
        return idMotor;
    }
    
    public void sell() {
        try {
            Connection connection = DriverManager.getConnection(dbURL,"APP","APP"); 
            Statement s = connection.createStatement();
            String sql = "DELETE FROM MOTORS WHERE id_motor="+this.idMotor;
            s.execute(sql);
        } catch (Exception except) {
            except.printStackTrace();
        }
    }
    
    public void lend() {
        try {
            Connection connection = DriverManager.getConnection(dbURL,"APP","APP"); 
            Statement s = connection.createStatement();
            String sql = "UPDATE MOTORS SET DOSTEPNOSC = 'false' WHERE id_motor = " + this.idMotor;
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
            String sql = "UPDATE MOTORS SET DOSTEPNOSC = 'true' WHERE id_motor = " + this.idMotor;
            this.dostepnosc = "true";
            s.execute(sql);
        } catch (Exception except) {
            except.printStackTrace();
        }
    }

    public Integer getIdMotor() {
        return idMotor;
    }

    public void setIdMotor(Integer idMotor) {
        this.idMotor = idMotor;
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
        hash += (idMotor != null ? idMotor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Motors)) {
            return false;
        }
        Motors other = (Motors) object;
        if ((this.idMotor == null && other.idMotor != null) || (this.idMotor != null && !this.idMotor.equals(other.idMotor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Marka: " + this.marka + ", Model: " + this.model + ", Cena: " + this.cena + ", Dostepnosc: " + this.dostepnosc;
    }
    
}
