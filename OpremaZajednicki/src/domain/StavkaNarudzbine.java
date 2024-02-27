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
public class StavkaNarudzbine extends AbstractDomainObject {

    private Narudzbina narudzbina;
    private int rbStavke;
    private int kolicina;
    private double cenaStavke;
    private Oprema oprema;

    public StavkaNarudzbine(Narudzbina narudzbina, int rbStavke, int kolicina, double cenaStavke, Oprema oprema) {
        this.narudzbina = narudzbina;
        this.rbStavke = rbStavke;
        this.kolicina = kolicina;
        this.cenaStavke = cenaStavke;
        this.oprema = oprema;
    }

    public StavkaNarudzbine() {
    }

    @Override
    public String nazivTabele() {
        return " StavkaNarudzbine ";
    }

    @Override
    public String alijas() {
        return " sn ";
    }

    @Override
    public String join() {
        return " JOIN NARUDZBINA N ON (N.NARUDZBINAID = SN.NARUDZBINAID) "
                + "JOIN KOMPANIJA K ON (K.KOMPANIJAID = N.KOMPANIJAID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = N.ADMINISTRATORID) "
                + "JOIN OPREMA O ON (O.OPREMAID = SN.OPREMAID) "
                + "JOIN TIPOPREME TOP ON (TOP.TIPOPREMEID = O.TIPOPREMEID) ";
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

            Narudzbina narudzbina = new Narudzbina(rs.getLong("NarudzbinaID"),
                    rs.getTimestamp("DatumVreme"), rs.getDate("DatumIsporuke"),
                    rs.getDouble("UkupnaCena"), k, a, null);

            TipOpreme to = new TipOpreme(rs.getLong("TipOpremeID"),
                    rs.getString("NazivTipaOpreme"));

            Oprema o = new Oprema(rs.getLong("OpremaID"), rs.getString("NazivOpreme"),
                    rs.getString("Opis"), rs.getInt("GodinaGarancije"),
                    rs.getDouble("Cena"), to);

            StavkaNarudzbine sn = new StavkaNarudzbine(narudzbina,
                    rs.getInt("RbStavke"), rs.getInt("Kolicina"),
                    rs.getDouble("CenaStavke"), o);

            lista.add(sn);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (NarudzbinaID, RbStavke, Kolicina, CenaStavke, OpremaID) ";
    }

    @Override
    public String uslov() {
        return " NarudzbinaID = " + narudzbina.getNarudzbinaID();
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + narudzbina.getNarudzbinaID() + ", " + rbStavke + ", "
                + " " + kolicina + ", " + cenaStavke + ", " + oprema.getOpremaID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        return " WHERE N.NARUDZBINAID = " + narudzbina.getNarudzbinaID();
    }

    public Narudzbina getNarudzbina() {
        return narudzbina;
    }

    public void setNarudzbina(Narudzbina narudzbina) {
        this.narudzbina = narudzbina;
    }

    public int getRbStavke() {
        return rbStavke;
    }

    public void setRbStavke(int rbStavke) {
        this.rbStavke = rbStavke;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCenaStavke() {
        return cenaStavke;
    }

    public void setCenaStavke(double cenaStavke) {
        this.cenaStavke = cenaStavke;
    }

    public Oprema getOprema() {
        return oprema;
    }

    public void setOprema(Oprema oprema) {
        this.oprema = oprema;
    }

}
