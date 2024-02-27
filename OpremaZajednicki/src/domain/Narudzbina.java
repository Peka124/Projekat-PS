/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author FUJITSU
 */
public class Narudzbina extends AbstractDomainObject {

    private Long narudzbinaID;
    private Date datumVreme;
    private Date datumIsporuke;
    private double ukupnaCena;
    private Kompanija kompanija;
    private Administrator administrator;
    private ArrayList<StavkaNarudzbine> stavkeNarudzbine;

    public Narudzbina(Long narudzbinaID, Date datumVreme, Date datumIsporuke, double ukupnaCena, Kompanija kompanija, Administrator administrator, ArrayList<StavkaNarudzbine> stavkeNarudzbine) {
        this.narudzbinaID = narudzbinaID;
        this.datumVreme = datumVreme;
        this.datumIsporuke = datumIsporuke;
        this.ukupnaCena = ukupnaCena;
        this.kompanija = kompanija;
        this.administrator = administrator;
        this.stavkeNarudzbine = stavkeNarudzbine;
    }

    public Narudzbina() {
    }

    @Override
    public String nazivTabele() {
        return " Narudzbina ";
    }

    @Override
    public String alijas() {
        return " n ";
    }

    @Override
    public String join() {
        return " JOIN KOMPANIJA K ON (K.KOMPANIJAID = N.KOMPANIJAID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = N.ADMINISTRATORID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            Kompanija k = new Kompanija(rs.getLong("KompanijaID"),
                    rs.getString("PIB"), rs.getString("NazivKompanije"),
                    rs.getString("Adresa"), rs.getString("Email"));

            Narudzbina narudzbina = new Narudzbina(rs.getLong("narudzbinaID"),
                    rs.getTimestamp("DatumVreme"), rs.getDate("DatumIsporuke"),
                    rs.getDouble("UkupnaCena"), k, a, null);

            lista.add(narudzbina);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (DatumVreme, DatumIsporuke, UkupnaCena, KompanijaID, AdministratorID) ";
    }

    @Override
    public String uslov() {
        return " NarudzbinaID = " + narudzbinaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new Timestamp(datumVreme.getTime()) + "', "
                + "'" + new java.sql.Date(datumIsporuke.getTime()) + "', "
                + " " + ukupnaCena + ", " + kompanija.getKompanijaID() + ", "
                + administrator.getAdministratorID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " DatumIsporuke = '" + new java.sql.Date(datumIsporuke.getTime()) + "', "
                + "UkupnaCena = " + ukupnaCena + " ";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getNarudzbinaID() {
        return narudzbinaID;
    }

    public void setNarudzbinaID(Long narudzbinaID) {
        this.narudzbinaID = narudzbinaID;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public Date getDatumIsporuke() {
        return datumIsporuke;
    }

    public void setDatumIsporuke(Date datumIsporuke) {
        this.datumIsporuke = datumIsporuke;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public Kompanija getKompanija() {
        return kompanija;
    }

    public void setKompanija(Kompanija kompanija) {
        this.kompanija = kompanija;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public ArrayList<StavkaNarudzbine> getStavkeNarudzbine() {
        return stavkeNarudzbine;
    }

    public void setStavkeNarudzbine(ArrayList<StavkaNarudzbine> stavkeNarudzbine) {
        this.stavkeNarudzbine = stavkeNarudzbine;
    }

}
