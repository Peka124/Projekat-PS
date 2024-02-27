/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import kontroler.Kontroler;
import domain.Administrator;
import domain.Narudzbina;
import domain.Oprema;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.ServerskiOdgovor;
import Operacije.ResponseStatus;
import Operacije.Operation;

/**
 *
 * @author FUJITSU
 */
public class ObradaKlijentskihZahteva extends Thread {

    private Socket socket;

    ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                transfer.KlijentskiZahtev request = (transfer.KlijentskiZahtev) in.readObject();
                ServerskiOdgovor response = handleRequest(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
                //out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ServerskiOdgovor handleRequest(transfer.KlijentskiZahtev request) {
        ServerskiOdgovor response = new ServerskiOdgovor(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {
                case Operation.ADD_NARUDZBINA:
                    Kontroler.getInstance().addNarudzbina((Narudzbina) request.getData());
                    break;
                case Operation.ADD_OPREMA:
                    Kontroler.getInstance().addOprema((Oprema) request.getData());
                    break;
                case Operation.DELETE_NARUDZBINA:
                    Kontroler.getInstance().deleteNarudzbina((Narudzbina) request.getData());
                    break;
                case Operation.DELETE_OPREMA:
                    Kontroler.getInstance().deleteOprema((Oprema) request.getData());
                    break;
                case Operation.UPDATE_NARUDZBINA:
                    Kontroler.getInstance().updateNarudzbina((Narudzbina) request.getData());
                    break;
                case Operation.UPDATE_OPREMA:
                    Kontroler.getInstance().updateOprema((Oprema) request.getData());
                    break;
                case Operation.GET_ALL_ADMINISTRATOR:
                    response.setData(Kontroler.getInstance().getAllAdministrator());
                    break;
                case Operation.GET_ALL_KOMPANIJA:
                    response.setData(Kontroler.getInstance().getAllKompanija());
                    break;
                case Operation.GET_ALL_NARUDZBINA:
                    response.setData(Kontroler.getInstance().getAllNarudzbina());
                    break;
                case Operation.GET_ALL_OPREMA:
                    response.setData(Kontroler.getInstance().getAllOprema());
                    break;
                case Operation.GET_ALL_STAVKA_NARUDZBINE:
                    response.setData(Kontroler.getInstance().getAllStavkaNarudzbine((Narudzbina) request.getData()));
                    break;
                case Operation.GET_ALL_TIP_OPREME:
                    response.setData(Kontroler.getInstance().getAllTipOprema());
                    break;
                case Operation.LOGIN:
                    Administrator administrator = (Administrator) request.getData();
                    Administrator ulogovani = Kontroler.getInstance().login(administrator);
                    response.setData(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception e) {
            response.setResponseStatus(ResponseStatus.Error);
            response.setException(e);
        }
        return response;
    }

}
