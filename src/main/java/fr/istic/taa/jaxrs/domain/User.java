package fr.istic.taa.jaxrs.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.Collection;

@Entity
@DiscriminatorValue("User")
public class User extends Person implements Serializable {

    public User() {}


}
