package fr.istic.taa.jaxrs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
@DiscriminatorValue("Organizer")
public class Organizer extends Person implements Serializable {


    private Collection<Concert> concerts;


    public Organizer() {}

    @OneToMany(mappedBy = "organizer")
    @JsonIgnore
    public Collection<Concert> getConcert() {
        return concerts;
    }

    public void setConcert(Collection<Concert> concerts) {
        this.concerts = concerts;
    }
}
