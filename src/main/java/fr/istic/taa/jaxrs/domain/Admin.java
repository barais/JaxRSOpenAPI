package fr.istic.taa.jaxrs.domain;

import fr.istic.taa.jaxrs.dao.generic.AdminDao;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.Collection;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends Person implements Serializable {

    private Collection<Concert> concerts;


    @OneToMany(mappedBy = "admin")
    public Collection<Concert> getConcerts() {
        return concerts;
    }

    public void setConcerts(Collection<Concert> concerts) {
        this.concerts = concerts;
    }
}
