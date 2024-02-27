/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;

import kontroler.Kontroler;
import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Administrator;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author FUJITSU
 */
public class SOLogin extends AbstractSO {
    
    Administrator ulogovani;
    
    private void validateLoggedAdmin(AbstractDomainObject ado) throws Exception{
        if (!(ado instanceof Administrator)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Administrator!");
        }
        Administrator a = (Administrator) ado;
        
        for (Administrator admin : Kontroler.getInstance().listaUlogovanihAdmina) {
            if(admin.getUsername().equals(a.getUsername())){
                throw new Exception("Admin sa ovim kredencijalima je vec ulogovan!");
            }
        }
    }

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Administrator)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Administrator!");
        }
        validateLoggedAdmin(ado);
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        Administrator a = (Administrator) ado;

        ArrayList<Administrator> administratori
                = (ArrayList<Administrator>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Administrator administrator : administratori) {
            if (administrator.getUsername().equals(a.getUsername())
                    && administrator.getPassword().equals(a.getPassword())) {
                ulogovani = administrator;
                
                Kontroler.getInstance().listaUlogovanihAdmina.add(ulogovani);
                
                return;
            }
        }

        throw new Exception("Ne postoji administrator sa tim kredencijalima.");
        
    }

    public Administrator getUlogovani() {
        return ulogovani;
    }
    
    

}
