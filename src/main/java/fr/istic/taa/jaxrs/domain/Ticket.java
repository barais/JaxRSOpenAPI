package fr.istic.taa.jaxrs.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Entity
public class Ticket implements Serializable {
    private long id;
    private String texte;
    private User auteur;
    private Membresup membresup;
    private LocalDate dateCreation;
    private Etat etat;
    private List<Tag> tags;
    private List<Commentaire> commentaires;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
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


    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    @ManyToOne
    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    @ManyToMany
    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @ManyToOne
    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
}
