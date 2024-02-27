/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.stavkaNarudzbine;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.StavkaNarudzbine;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author FUJITSU
 */
public class SOGetAllStavkaNarudzbine extends AbstractSO {

    private ArrayList<StavkaNarudzbine> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof StavkaNarudzbine)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StavkaNarudzbine!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> stavkeNarudzbine = DBBroker.getInstance().select(ado);
        lista = (ArrayList<StavkaNarudzbine>) (ArrayList<?>) stavkeNarudzbine;
    }

    public ArrayList<StavkaNarudzbine> getLista() {
        return lista;
    }

}
