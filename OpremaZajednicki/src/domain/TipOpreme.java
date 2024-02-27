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
public class TipOpreme extends AbstractDomainObject {
    
    private Long tipOpremeID;
    private String nazivTipaOpreme;

    @Override
    public String toString() {
        return nazivTipaOpreme;
    }

    public TipOpreme(Long tipOpremeID, String nazivTipaOpreme) {
        this.tipOpremeID = tipOpremeID;
        this.nazivTipaOpreme = nazivTipaOpreme;
    }

    public TipOpreme() {
    }
    
    @Override
    public String nazivTabele() {
        return " TipOpreme ";
    }

    @Override
    public String alijas() {
        return " TOP ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            TipOpreme to = new TipOpreme(rs.getLong("TipOpremeID"),
                    rs.getString("NazivTipaOpreme"));

            lista.add(to);
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
        return " TipOpremeID = " + tipOpremeID;
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

    public Long getTipOpremeID() {
        return tipOpremeID;
    }

    public void setTipOpremeID(Long tipOpremeID) {
        this.tipOpremeID = tipOpremeID;
    }

    public String getNazivTipaOpreme() {
        return nazivTipaOpreme;
    }

    public void setNazivTipaOpreme(String nazivTipaOpreme) {
        this.nazivTipaOpreme = nazivTipaOpreme;
    }
}
