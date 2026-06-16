package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.Date;
import java.util.ArrayList;

public class JpaTest {

    private EntityManager manager;

    public JpaTest(EntityManager manager) {
        this.manager = manager;
    }

    public static void main(String[] args) {
        // 1. Récupération du manager via l'Helper
        EntityManager manager = EntityManagerHelper.getEntityManager();
        JpaTest test = new JpaTest(manager);

        // 2. Exécution de la persistance
        test.createData();

        // 3. Fermeture propre des ressources
        manager.close();
        EntityManagerHelper.closeEntityManagerFactory();
        System.out.println("Données persistées avec succès. Fin du test.");
    }

    public void createData() {
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {
            // --- 1. PERSONNES (Héritage) ---
            Admin admin = new Admin();
            admin.setFirstname("Marc");
            admin.setLastname("Admin");
            admin.setEmail("admin@festival.com");
            manager.persist(admin);

            Organizer organizer = new Organizer();
            organizer.setFirstname("Julie");
            organizer.setLastname("Events");
            organizer.setEmail("contact@prod.com");
            manager.persist(organizer);

            Artist artist = new Artist();
            artist.setFirstname("Stromae");
            artist.setLastname("Paul");
            artist.setEmail("artiste@music.be");
            manager.persist(artist);

            User user = new User();
            user.setFirstname("Jean");
            user.setLastname("Dupont");
            user.setEmail("jean.dupont@email.com");
            manager.persist(user);

            // --- 2. CONCERT ---
            Concert concert = new Concert();
            concert.setName("Tournée Multitude");
            concert.setDate(new Date());
            concert.setStartTime("20h");
            concert.setEndTime("23h");
            concert.setPrice(75L);
            concert.setMusicalGenre("Pop");
            concert.setLocation("Bercy, Paris");
            concert.setPlaceNumber(100);
            concert.setAvailableTickets(100);
            concert.setIsValidated(true);

            // Si Concert a une liste d'artistes (ManyToMany)
            if (concert.getArtists() == null) concert.setArtists(new ArrayList<>());
            concert.getArtists().add(artist);

            manager.persist(concert);

            // --- 3. TICKET (Liaison User <-> Concert) ---
            Ticket ticket = new Ticket();
            ticket.setPrice(concert.getPrice());
            ticket.setQuantity(1);
            ticket.setBuyerEmail(user.getEmail()); // L'email qui servira à la recherche Angular
            ticket.setConcert(concert);
            ticket.setDate(new java.util.Date());
            ticket.setStatus("confirmed"); // Statut initial pour l'affichage en vert (Valide)
            ticket.setCanceled(false);
            ticket.setRefunded(false);

            manager.persist(ticket);

            // --- 4. NOTIFICATION ---
            Notification notification = new Notification();
            notification.setContent("Votre commande pour " + concert.getName() + " est validée.");
            notification.setType("INFO_ACHAT");
            // notification.setPerson(user); // Si lié à une Person

            manager.persist(notification);

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }
}