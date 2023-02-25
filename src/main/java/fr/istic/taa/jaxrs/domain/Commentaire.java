package fr.istic.taa.jaxrs.domain;

import javax.persistence.*;

@Entity
public class Commentaire {

    private long id;
    @Id
    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
