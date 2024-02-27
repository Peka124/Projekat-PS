/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.tipOpreme;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.TipOpreme;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author FUJITSU
 */
public class SOGetAllTipOprema extends AbstractSO {

    private ArrayList<TipOpreme> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof TipOpreme)) {
            throw new Exception("Prosledjeni objekat nije instanca klase TipOpreme");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> tipoviOruzja = DBBroker.getInstance().select(ado);
        lista = (ArrayList<TipOpreme>) (ArrayList<?>) tipoviOruzja;
    }

    public ArrayList<TipOpreme> getLista() {
        return lista;
    }

}
