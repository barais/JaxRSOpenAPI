package fr.istic.taa.jaxrs.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Ticket {
    private long id;
    private User auteur;
    private Membresup membresup;

    @Id
    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    public User getAuteur() {
        return this.auteur;
    }

    public void setAuteur(User auteur) {
        this.auteur = auteur;
    }

    @ManyToOne
    public Membresup getMembresup() {
        return this.membresup;
    }

    public void setMembresup(Membresup membresup) {
        this.membresup = membresup;
    }
}
