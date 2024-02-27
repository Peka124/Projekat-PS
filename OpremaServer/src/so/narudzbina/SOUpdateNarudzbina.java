/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.narudzbina;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Narudzbina;
import domain.StavkaNarudzbine;
import java.util.Date;
import so.AbstractSO;

/**
 *
 * @author FUJITSU
 */
public class SOUpdateNarudzbina extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Narudzbina)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Narudzbina!");
        }
        
        Narudzbina narudzbina = (Narudzbina) ado;

        if (!narudzbina.getDatumIsporuke().after(new Date())) {
            throw new Exception("Datum isporuke mora biti u buducnosti!");
        }

        if (narudzbina.getStavkeNarudzbine().isEmpty()) {
            throw new Exception("Narudzbina mora imati barem jednu stavku!");
        }
        
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        
        
        DBBroker.getInstance().update(ado);
        
        Narudzbina narudzbina = (Narudzbina) ado;
        
        DBBroker.getInstance().delete(narudzbina.getStavkeNarudzbine().get(0));
        
        
        for (StavkaNarudzbine stavkaNarudzbine : narudzbina.getStavkeNarudzbine()) {
            DBBroker.getInstance().insert(stavkaNarudzbine);
        }
        
        
        
    }

}
