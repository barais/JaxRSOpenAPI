package fr.istic.taa.jaxrs.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class MembreSupport extends Utilisateur {
    private List<Ticket> ticketsAttribues;
    @OneToMany
    public List<Ticket> getTicketsAttribues() {
        return ticketsAttribues;
    }

    public void setTicketsAttribues(List<Ticket> ticketsAttribues) {
        this.ticketsAttribues = ticketsAttribues;
    }
}
