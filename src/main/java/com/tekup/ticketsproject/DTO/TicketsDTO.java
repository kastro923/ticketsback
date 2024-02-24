package com.tekup.ticketsproject.DTO;

import java.util.Date;

public class TicketsDTO {

    private Long id;
    private String title;
    private String description;
    private Date created_at;
    private String createdBy;
    private String status;


    public TicketsDTO() {
    }

    public TicketsDTO(Long id, String title, String description, Date created_at, String createdBy, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.created_at = created_at;
        this.createdBy = createdBy;
        this.status = status;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
