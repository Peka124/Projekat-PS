/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import komunikacija.Komunikacija;
import domain.Oprema;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author FUJITSU
 */
public class TableModelOpreme extends AbstractTableModel implements Runnable {

    private ArrayList<Oprema> lista;
    private String[] kolone = {"ID", "Tip opreme", "Naziv", "Godina garancije", "Cena"};
    private String parametar = "";

    public TableModelOpreme() {
        try {
            lista = Komunikacija.getInstance().getAllOprema();
        } catch (Exception ex) {
            Logger.getLogger(TableModelOpreme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Oprema o = lista.get(row);

        switch (column) {
            case 0:
                return o.getOpremaID();
            case 1:
                return o.getTipOpreme();
            case 2:
                return o.getNazivOpreme();
            case 3:
                return o.getGodinaGarancije();
            case 4:
                return o.getCena() + "€";

            default:
                return null;
        }
    }

    public Oprema getSelectedOprema(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelOpreme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = Komunikacija.getInstance().getAllOprema();
            if (!parametar.equals("")) {
                ArrayList<Oprema> novaLista = new ArrayList<>();
                for (Oprema o : lista) {
                    if (o.getNazivOpreme().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(o);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
