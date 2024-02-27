/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author FUJITSU
 */
public class KlijentskiZahtev implements Serializable {

    private int operation;
    private Object data;

    public KlijentskiZahtev() {
    }

    public KlijentskiZahtev(int operation, Object data) {
        this.operation = operation;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public int getOperation() {
        return operation;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

}
