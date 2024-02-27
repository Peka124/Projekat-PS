/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.narudzbina;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Narudzbina;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author FUJITSU
 */
public class SOGetAllNarudzbina extends AbstractSO {

    private ArrayList<Narudzbina> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Narudzbina)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Narudzbina!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> narudzbine = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Narudzbina>) (ArrayList<?>) narudzbine;
    }

    public ArrayList<Narudzbina> getLista() {
        return lista;
    }

}
