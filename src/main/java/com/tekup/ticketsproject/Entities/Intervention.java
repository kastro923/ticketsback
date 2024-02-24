package com.tekup.ticketsproject.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Intervention implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String details;

    private Date date;

    private String type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private Tickets ticket;


    public Intervention() {
    }

    public Intervention(Long id, String details, Date date, String type, Tickets ticket) {
        this.id = id;
        this.details = details;
        this.date = date;
        this.type = type;
        this.ticket = ticket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Tickets getTicket() {
        return ticket;
    }

    public void setTicket(Tickets ticket) {
        this.ticket = ticket;
    }
}
