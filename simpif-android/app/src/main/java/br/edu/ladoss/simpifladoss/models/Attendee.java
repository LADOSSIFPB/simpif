package br.edu.ladoss.simpifladoss.models;

import java.util.Date;

/**
 * Created by Rennan on 28/09/17.
 */

public class Attendee {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int privateRefNum;
    private boolean hasArrived;
    private Date arrivalTime;

    private int orderId;
    private Order order;

    public Attendee(int id, String firstName, String lastName, String email, int privateRefNum, boolean hasArrived, Date arrivalTime, int orderId, Order order) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.privateRefNum = privateRefNum;
        this.hasArrived = hasArrived;
        this.arrivalTime = arrivalTime;

        this.orderId = orderId;
        this.order = order;
    }

}
