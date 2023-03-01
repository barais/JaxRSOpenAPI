package fr.istic.taa.jaxrs.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Tag implements Serializable {
    private long id;
    private String libelle;
    private List<Ticket> tickets;
    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @ManyToMany
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return id == tag.id && Objects.equals(libelle, tag.libelle) && Objects.equals(tickets, tag.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, libelle, tickets);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", tickets=" + tickets +
                '}';
    }
}
