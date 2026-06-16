package fr.istic.taa.jaxrs.domain;

public class AdminStats {
    private Long totalConcerts;
    private Long totalUsers;
    private Long totalArtists;
    private Long totalOrganizers;
    private Long totalTickets;

    public Long getTotalConcerts() { return totalConcerts; }
    public void setTotalConcerts(Long totalConcerts) { this.totalConcerts = totalConcerts; }

    public Long getTotalUsers() { return totalUsers; }
    public void setTotalUsers(Long totalUsers) { this.totalUsers = totalUsers; }

    public Long getTotalArtists() { return totalArtists; }
    public void setTotalArtists(Long totalArtists) { this.totalArtists = totalArtists; }

    public Long getTotalOrganizers() { return totalOrganizers; }
    public void setTotalOrganizers(Long totalOrganizers) { this.totalOrganizers = totalOrganizers; }

    public Long getTotalTickets() { return totalTickets; }
    public void setTotalTickets(Long totalTickets) { this.totalTickets = totalTickets; }
}