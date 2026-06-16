package fr.istic.taa.jaxrs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
@DiscriminatorValue("Artist")
public class Artist extends Person implements Serializable {

    private Collection<Concert> concerts;

    public Artist() {}

    @ManyToMany
    @JsonIgnore
    public Collection<Concert> getConcerts() {
        return concerts;
    }


    public void setConcerts(Collection<Concert> concerts) {
        this.concerts = concerts;
    }
}
