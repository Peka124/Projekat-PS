/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domain.Administrator;
import domain.Kompanija;
import domain.Narudzbina;
import domain.Oprema;
import domain.StavkaNarudzbine;
import domain.TipOpreme;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.administrator.SOGetAllAdministrator;
import so.kompanija.SOGetAllKompanija;
import so.login.SOLogin;
import so.narudzbina.SOAddNarudzbina;
import so.narudzbina.SODeleteNarudzbina;
import so.narudzbina.SOGetAllNarudzbina;
import so.narudzbina.SOUpdateNarudzbina;
import so.oprema.SOAddOprema;
import so.oprema.SODeleteOprema;
import so.oprema.SOGetAllOprema;
import so.oprema.SOUpdateOprema;
import so.stavkaNarudzbine.SOGetAllStavkaNarudzbine;
import so.tipOpreme.SOGetAllTipOprema;

/**
 *
 * @author FUJITSU
 */
public class Kontroler {

    private static Kontroler instance;
    
    public ArrayList<Administrator> listaUlogovanihAdmina = new ArrayList<Administrator>();

    private Kontroler() {
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }


    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(administrator);
        return so.getUlogovani();
    }

    public void addOprema(Oprema oprema) throws Exception {
        (new SOAddOprema()).templateExecute(oprema);
    }

    public void addNarudzbina(Narudzbina narudzbina) throws Exception {
        (new SOAddNarudzbina()).templateExecute(narudzbina);
    }

    public void deleteOprema(Oprema oprema) throws Exception {
        (new SODeleteOprema()).templateExecute(oprema);
    }

    public void deleteNarudzbina(Narudzbina narudzbina) throws Exception {
        (new SODeleteNarudzbina()).templateExecute(narudzbina);
    }

    public void updateOprema(Oprema oprema) throws Exception {
        (new SOUpdateOprema()).templateExecute(oprema);
    }

    public void updateNarudzbina(Narudzbina narudzbina) throws Exception {
        (new SOUpdateNarudzbina()).templateExecute(narudzbina);
    }

    public ArrayList<Administrator> getAllAdministrator() throws Exception {
        SOGetAllAdministrator so = new SOGetAllAdministrator();
        so.templateExecute(new Administrator());
        return so.getLista();
    }

    public ArrayList<Oprema> getAllOprema() throws Exception {
        SOGetAllOprema so = new SOGetAllOprema();
        so.templateExecute(new Oprema());
        return so.getLista();
    }

    public ArrayList<Narudzbina> getAllNarudzbina() throws Exception {
        SOGetAllNarudzbina so = new SOGetAllNarudzbina();
        so.templateExecute(new Narudzbina());
        return so.getLista();
    }

    public ArrayList<TipOpreme> getAllTipOprema() throws Exception {
        SOGetAllTipOprema so = new SOGetAllTipOprema();
        so.templateExecute(new TipOpreme());
        return so.getLista();
    }

    public ArrayList<Kompanija> getAllKompanija() throws Exception {
        SOGetAllKompanija so = new SOGetAllKompanija();
        so.templateExecute(new Kompanija());
        return so.getLista();
    }

    public ArrayList<StavkaNarudzbine> getAllStavkaNarudzbine(Narudzbina narudzbina) throws Exception {
        SOGetAllStavkaNarudzbine so = new SOGetAllStavkaNarudzbine();
        
        StavkaNarudzbine sn = new StavkaNarudzbine();
        sn.setNarudzbina(narudzbina);
        
        so.templateExecute(sn);
        return so.getLista();
    }

}
