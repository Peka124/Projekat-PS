/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.oprema;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Oprema;
import so.AbstractSO;

/**
 *
 * @author FUJITSU
 */
public class SODeleteOprema extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Oprema)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Oprema");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}
