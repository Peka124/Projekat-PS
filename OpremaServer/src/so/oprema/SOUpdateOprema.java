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
public class SOUpdateOprema extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Oprema)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Oprema");
        }

        Oprema o = (Oprema) ado;

        if (o.getCena() < 5 || o.getCena() > 10000) {
            throw new Exception("Cena mora biti izmedju 5€ i 10000€");
        }

        ArrayList<Oprema> oprema = (ArrayList<Oprema>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Oprema op : oprema) {
            if (!op.getOpremaID().equals(op.getOpremaID())) {
                if (op.getNazivOpreme().equals(op.getNazivOpreme())) {
                    throw new Exception("Naziv opreme mora biti jedinstven!");
                }
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }

}
