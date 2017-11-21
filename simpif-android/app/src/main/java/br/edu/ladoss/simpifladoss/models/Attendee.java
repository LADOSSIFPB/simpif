package br.edu.ladoss.simpifladoss.models;

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

    private boolean isCancelled;

    private int order_id;

    private String ticket_title;

    public Attendee(int id, String firstName, String lastName, String email, int privateRefNum, boolean hasArrived, String arrivalTime, int order_id, String ticket_title, boolean isCancelled) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;

        this.email = email;
        this.privateRefNum = privateRefNum;
        this.hasArrived = hasArrived;
        this.arrivalTime = arrivalTime;

        this.order_id = order_id;

        this.ticket_title = ticket_title;

        this.isCancelled = isCancelled;
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
        return order_id;
    }

    public String getTicketTitle() {
        return ticket_title;
    }

    public boolean isCancelled() {
        return isCancelled;
    }
}
