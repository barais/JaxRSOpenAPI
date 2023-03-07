package fr.istic.taa.jaxrs.dto;

import java.time.LocalDate;
import java.util.Objects;

public class TicketDto {
    private String texte;
    private String auteurName;
    private String etat;
    private LocalDate date;

    public TicketDto() {
    }

    public TicketDto(String texte, String auteurName, String etat, LocalDate date) {
        this.texte = texte;
        this.auteurName = auteurName;
        this.etat = etat;
        this.date = date;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getAuteurName() {
        return auteurName;
    }

    public void setAuteurName(String auteurName) {
        this.auteurName = auteurName;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketDto ticketDto = (TicketDto) o;
        return Objects.equals(texte, ticketDto.texte) && Objects.equals(auteurName, ticketDto.auteurName) && Objects.equals(etat, ticketDto.etat) && Objects.equals(date, ticketDto.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(texte, auteurName, etat, date);
    }
}
