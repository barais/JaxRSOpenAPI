# 🎵 Stagely — Billetterie de Concerts

Plateforme de billetterie de concerts permettant aux utilisateurs de rechercher, consulter et acheter des tickets, aux organisateurs de gérer leurs événements, et aux administrateurs de valider et modérer les contenus.

---

## 🎬 Démo

▶️ [Voir la démo sur Google Drive](https://drive.google.com/file/d/1d064biE2iS1UYH3rhWBccb9QUndaaVQS/view)

---

## 🤖 Utilisation de l'IA

Le style et le design de l'interface utilisateur ont été en partie générés et affinés à l'aide de **Claude (Anthropic)**. L'IA a été utilisée pour :

- Générer les feuilles de style CSS des composants (user-list, artist-list, organizer-list, admin-dashboard, formulaires...)
- Assurer la cohérence visuelle entre les pages (palette de couleurs, typographie, espacements)
- Rendre les interfaces responsives (media queries, transformation des tableaux en cards sur mobile)
- Suggérer et implémenter des patterns de design (badges de statut, cards de navigation, tableaux stylisés)

---

## 📋 Modèle Métier

### Diagramme de classes

![Diagramme de classes](diag-de-classe.png)

### Entités principales

| Entité | Description |
|--------|-------------|
| `Person` | Classe abstraite parente (héritage SINGLE_TABLE) |
| `User` | Utilisateur / acheteur de tickets |
| `Admin` | Administrateur de la plateforme |
| `Artist` | Artiste musical |
| `Organizer` | Organisateur d'événements |
| `Concert` | Événement musical |
| `Ticket` | Billet d'entrée (lié au concert, sans relation directe User — utilise `buyerEmail`) |
| `Notification` | Alerte liée à un concert |
| `AdminStats` | DTO de statistiques globales |

### Relations
- `Person` → héritage `SINGLE_TABLE` avec discriminateur `Type`
- `Concert` → `Ticket` : OneToMany (`@JsonIgnore` sur tickets)
- `Concert` → `Artist` : ManyToMany (bidirectionnel)
- `Concert` → `Notification` : OneToMany
- `Concert` → `Admin` : ManyToOne
- `Concert` → `Organizer` : ManyToOne (`@JsonIgnore` côté Organizer)
- `Ticket` → `Concert` : ManyToOne (sans relation User — utilise `buyerEmail` et `transferorEmail`)

---

## 🚀 Lancement du projet

### Prérequis
- Java 17+
- Maven
- Node.js 18+
- Angular CLI

### Backend (JAX-RS)

```bash
# 1. Démarrer le serveur HSQLDB
cd JaxRSOpenAPI/data
java -cp ../target/dependency/hsqldb-2.7.2.jar org.hsqldb.Server

# 2. Initialiser les données (première fois)
# Lancer JpaTest depuis IntelliJ

# 3. Démarrer le serveur REST
# Lancer RestServer depuis IntelliJ
# → http://localhost:8080
```

### Frontend (Angular)

```bash
cd BilleterieFrontend
npm install
ng serve
# → http://localhost:4200
```

---

## 🌐 Endpoints API REST

### 👤 Users `/api/users`
| Méthode | URL | Description |
|---------|-----|-------------|
| GET | `/users/` | Liste tous les utilisateurs |
| GET | `/users/{id}` | Détails d'un utilisateur |
| POST | `/users/` | Créer un utilisateur |
| PUT | `/users/{id}` | Modifier un utilisateur |
| DELETE | `/users/{id}` | Supprimer un utilisateur |

### 🎤 Artists `/api/artists`
| Méthode | URL | Description |
|---------|-----|-------------|
| GET | `/artists/` | Liste tous les artistes |
| GET | `/artists/{id}` | Détails d'un artiste |
| POST | `/artists/` | Créer un artiste |
| PUT | `/artists/{id}` | Modifier un artiste |
| DELETE | `/artists/{id}` | Supprimer un artiste |

### 🎪 Organizers `/api/organizers`
| Méthode | URL | Description |
|---------|-----|-------------|
| GET | `/organizers/` | Liste tous les organisateurs |
| GET | `/organizers/{id}` | Détails d'un organisateur |
| POST | `/organizers/` | Créer un organisateur |
| PUT | `/organizers/{id}` | Modifier un organisateur |
| DELETE | `/organizers/{id}` | Supprimer un organisateur |

### 🔧 Admins `/api/admins`
| Méthode | URL | Description |
|---------|-----|-------------|
| GET | `/admins/` | Liste tous les admins |
| GET | `/admins/{id}` | Détails d'un admin |
| POST | `/admins/` | Créer un admin |
| PUT | `/admins/{id}` | Modifier un admin |
| DELETE | `/admins/{id}` | Supprimer un admin |

### 🎵 Concerts `/api/concerts`
| Méthode | URL | Description |
|---------|-----|-------------|
| GET | `/concerts/` | Liste tous les concerts |
| GET | `/concerts/{id}` | Détails d'un concert |
| POST | `/concerts/` | Créer un concert |
| PUT | `/concerts/{id}` | Modifier un concert |
| DELETE | `/concerts/{id}` | Supprimer un concert |
| GET | `/concerts/search?q=` | Recherche par artiste, lieu, nom, genre |
| GET | `/concerts/location?q=` | Filtrer par lieu |
| GET | `/concerts/validated` | Concerts validés uniquement |
| GET | `/concerts/maxprice?price=` | Filtrer par prix maximum |
| GET | `/concerts/sortByPopularity` | Trier par popularité |
| GET | `/concerts/sortByPrice` | Trier par prix |
| GET | `/concerts/sortByDate` | Trier par date |
| GET | `/concerts/date?q=` | Rechercher par date |

### 🎟️ Tickets `/api/tickets`
| Méthode | URL | Description |
|---------|-----|-------------|
| GET | `/tickets/` | Liste tous les tickets |
| GET | `/tickets/{id}` | Détails d'un ticket |
| DELETE | `/tickets/{id}` | Supprimer un ticket |
| POST | `/tickets/buy` | Acheter un ticket |
| GET | `/tickets/user/{userId}` | Tickets d'un utilisateur |
| PUT | `/tickets/{id}/cancel` | Annuler un ticket |
| PUT | `/tickets/{id}/refund` | Rembourser un ticket |
| PUT | `/tickets/{id}/transfer` | Transférer un ticket |

### 🔔 Notifications `/api/notifications`
| Méthode | URL | Description |
|---------|-----|-------------|
| GET | `/notifications/` | Liste toutes les notifications |
| GET | `/notifications/{id}` | Détails d'une notification |
| DELETE | `/notifications/{id}` | Supprimer une notification |

### 📊 Stats `/api/stats`
| Méthode | URL | Description |
|---------|-----|-------------|
| GET | `/stats/` | Statistiques globales (concerts, users, artistes, organisateurs, tickets) |

---

## 🎨 Frontend — Graphe de Navigation

```
/ (Accueil)
├── /concerts              → Liste des concerts
│   ├── /concerts/create   → Créer un concert
│   └── /concerts/:id      → Détail d'un concert + achat ticket
│
├── /tickets               → Mes tickets
│   ├── Annuler un ticket
│   ├── Rembourser un ticket
│   └── Transférer un ticket
│
├── /admin                 → Tableau de bord admin
│   └── /admin/concerts    → Gestion et validation des concerts
│       ├── /users         → Gestion des utilisateurs
│       ├── /artists       → Gestion des artistes
│       └── /organizers    → Gestion des organisateurs
│
├── /users                 → Liste des utilisateurs
│   ├── /users/create      → Créer un utilisateur
│   ├── /users/:id         → Détails d'un utilisateur
│   └── /users/:id/edit    → Modifier un utilisateur
│
├── /artists               → Liste des artistes
│   ├── /artists/create    → Créer un artiste
│   ├── /artists/:id       → Détails d'un artiste
│   └── /artists/:id/edit  → Modifier un artiste
│
└── /organizers            → Liste des organisateurs
    ├── /organizers/create → Créer un organisateur
    ├── /organizers/:id    → Détails d'un organisateur
    └── /organizers/:id/edit → Modifier un organisateur
```

---

## 🗄️ DAO — Requêtes spéciales

### JPQL
```java
// Recherche multi-critères dans ConcertDao
SELECT DISTINCT c FROM Concert c LEFT JOIN c.artists a
WHERE LOWER(a.firstname) LIKE :q OR LOWER(c.name) LIKE :q ...

// COUNT optimisé pour les stats
SELECT COUNT(c) FROM Concert c
SELECT COUNT(p) FROM Person p WHERE TYPE(p) = User
SELECT COUNT(p) FROM Person p WHERE TYPE(p) = Artist
SELECT COUNT(p) FROM Person p WHERE TYPE(p) = Organizer
SELECT COUNT(t) FROM Ticket t
```

### Requêtes nommées (@NamedQuery)
```java
Concert.findByLocation           // Concerts par lieu
Concert.findValidated            // Concerts validés
Concert.findByDate               // Concerts par date
Concert.findAllOrderByPopularity // Tri par popularité
Concert.findAllOrderByPrice      // Tri par prix
Concert.findAllOrderByDate       // Tri par date
```

### Criteria Query
```java
// Concerts dont le prix est <= maxPrice
CriteriaBuilder cb = entityManager.getCriteriaBuilder();
cq.select(root).where(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
```

---

## 📚 Documentation OpenAPI

La documentation OpenAPI est disponible sur :
```
http://localhost:8080/openapi.json
```

Visualisable sur [Swagger Editor](https://editor.swagger.io) en important l'URL.

---

## 🛠️ Stack Technique

### Backend
- Java 26
- JAX-RS (RESTEasy 6.2)
- Hibernate 6.2 / JPA 3.1
- HSQLDB 2.7
- Undertow 2.3
- Swagger / OpenAPI 3
- Jackson (sérialisation JSON)

### Frontend
- Angular 19
- Angular Material
- TypeScript
- RxJS
- Proxy Angular (`/api` → `http://localhost:8080`)

---