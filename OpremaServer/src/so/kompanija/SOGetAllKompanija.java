/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.kompanija;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Kompanija;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author FUJITSU
 */
public class SOGetAllKompanija extends AbstractSO {

    private ArrayList<Kompanija> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Kompanija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Kompanija!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> kompanije = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Kompanija>) (ArrayList<?>) kompanije;
    }

    public ArrayList<Kompanija> getLista() {
        return lista;
    }

}
