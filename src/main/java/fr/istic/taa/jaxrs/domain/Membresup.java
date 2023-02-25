package fr.istic.taa.jaxrs.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Membresup extends User {
    
    private List<Ticket> TicketsAttribues;

    @OneToMany
    public List<Ticket> getTicketsAttribues() {
        return this.TicketsAttribues;
    }

    public void setTicketsAttribues(List<Ticket> TicketsAttribues) {
        this.TicketsAttribues = TicketsAttribues;
    }


}
