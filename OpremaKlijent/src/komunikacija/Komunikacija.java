/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domain.Administrator;
import domain.Kompanija;
import domain.Narudzbina;
import domain.Oprema;
import domain.StavkaNarudzbine;
import domain.TipOpreme;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import Operacije.ResponseStatus;
import Operacije.Operation;

/**
 *
 * @author FUJITSU
 */
public class Komunikacija {

    private static Komunikacija instance;

    private Komunikacija() {
    }

    public static Komunikacija getInstance() {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        return (Administrator) sendRequest(Operation.LOGIN, administrator);
    }

    public void addOprema(Oprema oprema) throws Exception {
        sendRequest(Operation.ADD_OPREMA, oprema);
    }

    public void addNarudzbina(Narudzbina narudzbina) throws Exception {
        sendRequest(Operation.ADD_NARUDZBINA, narudzbina);
    }

    public void deleteOprema(Oprema oprema) throws Exception {
        sendRequest(Operation.DELETE_OPREMA, oprema);
    }

    public void deleteNarudzbina(Narudzbina narudzbina) throws Exception {
        sendRequest(Operation.DELETE_NARUDZBINA, narudzbina);
    }

    public void updateOprema(Oprema oprema) throws Exception {
        sendRequest(Operation.UPDATE_OPREMA, oprema);
    }

    public void updateNarudzbina(Narudzbina narudzbina) throws Exception {
        sendRequest(Operation.UPDATE_NARUDZBINA, narudzbina);
    }

    public ArrayList<Administrator> getAllAdministrator() throws Exception {
        return (ArrayList<Administrator>) sendRequest(Operation.GET_ALL_ADMINISTRATOR, null);
    }

    public ArrayList<Oprema> getAllOprema() throws Exception {
        return (ArrayList<Oprema>) sendRequest(Operation.GET_ALL_OPREMA, null);
    }

    public ArrayList<Narudzbina> getAllNarudzbina() throws Exception {
        return (ArrayList<Narudzbina>) sendRequest(Operation.GET_ALL_NARUDZBINA, null);
    }

    public ArrayList<TipOpreme> getAllTipOpreme() throws Exception {
        return (ArrayList<TipOpreme>) sendRequest(Operation.GET_ALL_TIP_OPREME, null);
    }

    public ArrayList<Kompanija> getAllKompanija() throws Exception {
        return (ArrayList<Kompanija>) sendRequest(Operation.GET_ALL_KOMPANIJA, null);
    }

    public ArrayList<StavkaNarudzbine> getAllStavkaNarudzbine(Narudzbina narudzbina) throws Exception {
        return (ArrayList<StavkaNarudzbine>) sendRequest(Operation.GET_ALL_STAVKA_NARUDZBINE, narudzbina);
    }

    private Object sendRequest(int operation, Object data) throws Exception {
        KlijentskiZahtev request = new KlijentskiZahtev(operation, data);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        ServerskiOdgovor response = (ServerskiOdgovor) in.readObject();

        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getException();
        } else {
            return response.getData();
        }

    }
}
