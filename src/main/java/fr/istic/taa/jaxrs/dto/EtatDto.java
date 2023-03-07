package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.Ticket;

import java.util.List;
import java.util.Objects;

public class EtatDto {
    private String libelle;
    private List<Ticket> tickets;

    public EtatDto() {
    }

    public EtatDto(String libelle,List<Ticket> tickets) {
        this.libelle = libelle;
        this.tickets=tickets;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

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
        EtatDto etatDto = (EtatDto) o;
        return Objects.equals(libelle, etatDto.libelle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libelle);
    }
}
