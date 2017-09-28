package br.edu.ladoss.simpifladoss.models;

/**
 * Created by Rennan on 28/09/17.
 */

public class Order {

    private int id;
    private String orderReference;

    public Order(int id, String orderReference) {
        this.id = id;
        this.orderReference = orderReference;
    }

}
