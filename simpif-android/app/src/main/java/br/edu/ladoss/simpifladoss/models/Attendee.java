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
    private String arrivalTime;

    private int orderId;

    public Attendee(int id, String firstName, String lastName, String email, int privateRefNum, boolean hasArrived, String arrivalTime, int orderId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;

        this.email = email;
        this.privateRefNum = privateRefNum;
        this.hasArrived = hasArrived;
        this.arrivalTime = arrivalTime;

        this.orderId = orderId;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getPrivateRefNum() {
        return privateRefNum;
    }

    public boolean hasArrived() {
        return hasArrived;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public int getOrderId() {
        return orderId;
    }

}
