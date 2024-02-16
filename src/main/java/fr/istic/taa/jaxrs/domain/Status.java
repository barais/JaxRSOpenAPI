package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@XmlRootElement(name = "Pet")
public class Status implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlAttribute
    private int id;

    @XmlAttribute
    private String userStatus;

    @OneToMany(mappedBy = "status", cascade = CascadeType.REMOVE)
    private List<Ticket> tickets = new ArrayList<>();

    public Status() {
        this.userStatus = "OPEN";
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setTickets(Ticket ticket) {
        this.tickets.add(ticket);
    }
}