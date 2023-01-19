package fr.istic.taa.jaxrs.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Utilisateur implements Serializable {
    private long id;
    private String nom;
    private List<Ticket> tickets;
    private List<Reaction> reactions;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    @OneToMany
    public List<Ticket> getTickets() {
        return tickets;
    }

    @OneToMany
    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
