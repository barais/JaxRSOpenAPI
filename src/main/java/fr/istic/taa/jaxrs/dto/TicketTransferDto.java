package fr.istic.taa.jaxrs.dto;

public class TicketTransferDto {
    private String newUserEmail;

    // Ajoute ce constructeur vide !
    public TicketTransferDto() {}

    public String getNewUserEmail() { return newUserEmail; }
    public void setNewUserEmail(String newUserEmail) { this.newUserEmail = newUserEmail; }
}