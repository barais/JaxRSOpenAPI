package fr.istic.taa.jaxrs.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Etat implements Serializable {
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

    @OneToMany
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
        Etat etat = (Etat) o;
        return id == etat.id && Objects.equals(libelle, etat.libelle) && Objects.equals(tickets, etat.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, libelle, tickets);
    }

    @Override
    public String toString() {
        return "Etat{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", tickets=" + tickets +
                '}';
    }
}
