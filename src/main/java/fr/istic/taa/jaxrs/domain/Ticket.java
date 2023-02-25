package fr.istic.taa.jaxrs.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ticket {
    private long id;
    @Id
    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
