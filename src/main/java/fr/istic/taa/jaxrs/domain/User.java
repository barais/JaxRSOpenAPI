package fr.istic.taa.jaxrs.domain;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class User implements Serializable {

    private long id;
    private String nom;
    private List<Ticket> TicketsCrees;

    @Id
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @OneToMany
    public List<Ticket> getTicketsCrees() {
        return this.TicketsCrees;
    }

    public void setTicketsCrees(List<Ticket> TicketsCrees) {
        this.TicketsCrees = TicketsCrees;
    }

    
}
