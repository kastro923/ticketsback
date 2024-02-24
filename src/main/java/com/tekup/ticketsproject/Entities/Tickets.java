package com.tekup.ticketsproject.Entities;

import com.tekup.ticketsproject.Entities.Enum.STATUS;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Tickets implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private Date created_at;
    private Date closed_at;
    private STATUS status;
    private String type;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;
    @ManyToOne
    @JoinColumn(name = "treated_by_id")
    private User treatedBy;

    @OneToOne(mappedBy = "ticket")
    private Intervention intervention;


    public Tickets() {
    }

    public Tickets(Long id, String title, String description, Date created_at, Date closed_at, STATUS status, String type, User createdBy, User treatedBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.created_at = created_at;
        this.closed_at = closed_at;
        this.status = status;
        this.type = type;
        this.createdBy = createdBy;
        this.treatedBy = treatedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getClosed_at() {
        return closed_at;
    }

    public void setClosed_at(Date closed_at) {
        this.closed_at = closed_at;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getTreatedBy() {
        return treatedBy;
    }

    public void setTreatedBy(User treatedBy) {
        this.treatedBy = treatedBy;
    }

    public Intervention getIntervention() {
        return intervention;
    }

    public void setIntervention(Intervention intervention) {
        this.intervention = intervention;
    }
}
