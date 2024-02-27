/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.oprema;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Oprema;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author FUJITSU
 */
public class SOAddOprema extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Oprema)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Oprema!");
        }

        Oprema o = (Oprema) ado;

        if (o.getGodinaGarancije()< 2 || o.getGodinaGarancije()> 20) {
            throw new Exception("Godina garancijes mora biti izmedju 2 i 20");
        }

        if (o.getCena() < 5 || o.getCena() > 10000) {
            throw new Exception("Cena mora biti izmedju 50€ i 10000€");
        }

        ArrayList<Oprema> oprema = (ArrayList<Oprema>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Oprema op : oprema) {
            if (op.getNazivOpreme().equals(o.getNazivOpreme())) {
                throw new Exception("Naziv opreme mora biti jedinstven!");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
