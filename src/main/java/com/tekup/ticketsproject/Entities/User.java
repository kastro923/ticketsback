package com.tekup.ticketsproject.Entities;

import com.tekup.ticketsproject.Entities.Enum.ROLES;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "User")
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String mail;
    private String password;
    private ROLES role;
    private Boolean isEnabled=true;

    @OneToMany(mappedBy="createdBy")
    private List<Tickets> created;
    @OneToMany(mappedBy="treatedBy")
    private List<Tickets> treated;

    public User() {
    }

    public User(Long id, String name, String mail, String password, ROLES role) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ROLES getRole() {
        return role;
    }

    public void setRole(ROLES role) {
        this.role = role;
    }

    public List<Tickets> getTreated() {
        return treated;
    }

    public void setTreated(List<Tickets> treated) {
        this.treated = treated;
    }

    public List<Tickets> getCreated() {
        return created;
    }

    public void setCreated(List<Tickets> created) {
        this.created = created;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }
}
