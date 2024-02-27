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
public class Oprema extends AbstractDomainObject {

    private Long opremaID;
    private String nazivOpreme;
    private String opis;
    private int godinaGarancije;
    private double cena;
    private TipOpreme tipOpreme;

    @Override
    public String toString() {
        return nazivOpreme + " (Tip: " + tipOpreme + ", Godina garancije: " + godinaGarancije
                + ", Cena: " + cena + "€)";
    }

    public Oprema(Long opremaID, String nazivOpreme, String opis, int godinaGarancije, double cena, TipOpreme tipOpreme) {
        this.opremaID = opremaID;
        this.nazivOpreme = nazivOpreme;
        this.opis = opis;
        this.godinaGarancije = godinaGarancije;
        this.cena = cena;
        this.tipOpreme = tipOpreme;
    }

    public Oprema() {
    }

    @Override
    public String nazivTabele() {
        return " Oprema ";
    }

    @Override
    public String alijas() {
        return " o ";
    }

    @Override
    public String join() {
        return " JOIN TIPOPREME TOP ON (TOP.TIPOPREMEID = O.TIPOPREMEID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            TipOpreme to = new TipOpreme(rs.getLong("TipOpremeID"),
                    rs.getString("NazivTipaOpreme"));

            Oprema o = new Oprema(rs.getLong("OpremaID"), rs.getString("NazivOpreme"),
                    rs.getString("Opis"), rs.getInt("GodinaGarancije"),
                    rs.getDouble("Cena"), to);

            lista.add(o);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (NazivOpreme, Opis, GodinaGarancije, Cena, TipOpremeID) ";
    }

    @Override
    public String uslov() {
        return " OpremaID = " + opremaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + nazivOpreme + "', '" + opis + "', "
                + " " + godinaGarancije + ", " + cena + ", " + tipOpreme.getTipOpremeID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " NazivOpreme = '" + nazivOpreme + "', Opis = '" + opis + "', "
                + "Cena = " + cena + " ";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getOpremaID() {
        return opremaID;
    }

    public void setOpremaID(Long opremaID) {
        this.opremaID = opremaID;
    }

    public String getNazivOpreme() {
        return nazivOpreme;
    }

    public void setNazivOpreme(String nazivOpreme) {
        this.nazivOpreme = nazivOpreme;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getGodinaGarancije() {
        return godinaGarancije;
    }

    public void setGodinaGarancije(int godinaGarancije) {
        this.godinaGarancije = godinaGarancije;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public TipOpreme getTipOpreme() {
        return tipOpreme;
    }

    public void setTipOpreme(TipOpreme tipOpreme) {
        this.tipOpreme = tipOpreme;
    }

}
