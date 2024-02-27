/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author FUJITSU
 */
public class Kompanija extends AbstractDomainObject {

    private Long kompanijaID;
    private String PIB;
    private String nazivKompanije;
    private String adresa;
    private String email;

    @Override
    public String toString() {
        return nazivKompanije;
    }

    public Kompanija(Long kompanijaID, String PIB, String nazivKompanije, String adresa, String email) {
        this.kompanijaID = kompanijaID;
        this.PIB = PIB;
        this.nazivKompanije = nazivKompanije;
        this.adresa = adresa;
        this.email = email;
    }

    public Kompanija() {
    }

    @Override
    public String nazivTabele() {
        return " Kompanija ";
    }

    @Override
    public String alijas() {
        return " k ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Kompanija k = new Kompanija(rs.getLong("KompanijaID"),
                    rs.getString("PIB"), rs.getString("NazivKompanije"),
                    rs.getString("Adresa"), rs.getString("Email"));

            lista.add(k);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String uslov() {
        return " KompanijaID = " + kompanijaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getKompanijaID() {
        return kompanijaID;
    }

    public void setKompanijaID(Long kompanijaID) {
        this.kompanijaID = kompanijaID;
    }

    public String getPIB() {
        return PIB;
    }

    public void setPIB(String PIB) {
        this.PIB = PIB;
    }

    public String getNazivKompanije() {
        return nazivKompanije;
    }

    public void setNazivKompanije(String nazivKompanije) {
        this.nazivKompanije = nazivKompanije;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
