import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private GestionnaireBibliotheque gestionnaire;
    private Scanner scanner;

    public Menu(GestionnaireBibliotheque gestionnaire) {
        this.gestionnaire = gestionnaire;
        this.scanner = new Scanner(System.in);
    }

    public void afficherMenuPrincipal() {
        System.out.println("Menu principal :");
        System.out.println("1. Adhérents");
        System.out.println("2. Emprunts");
        System.out.println("3. Documents");
        System.out.println("0. Quitter");
        System.out.print("Choix : ");
        int choix = scanner.nextInt();

        switch (choix) {
            case 1:
                afficherMenuAdherents();
                break;
            case 2:
                afficherMenuEmprunts();
                break;
            case 3:
                afficherMenuDocuments();
                break;
            case 0:
                System.out.println("Au revoir !");
                System.exit(0);
                break;
            default:
                System.out.println("Choix invalide !");
        }
    }

    public void afficherMenuAdherents() {
        System.out.println("Menu Adhérents :");
        System.out.println("1. Créer un nouvel adhérent");
        System.out.println("2. Supprimer un adhérent");
        System.out.println("3. Modifier un adhérent");
        System.out.println("4. Rechercher un adhérent par type");
        System.out.println("5. Rechercher un adhérent avec Identifiant");
        System.out.println("6. Afficher la liste des adhérents");
        System.out.println("0. Retour au menu principal");
        System.out.print("Choix : ");
        int choix = scanner.nextInt();

        switch (choix) {
            case 1:
                // Ajouter un adhérent
                System.out.println("Ajout d'un nouvel adhérent :");
                System.out.print("Nom : ");
                String nom = scanner.next();
                System.out.print("Prénom : ");
                String prenom = scanner.next();
                System.out.print("Identifiant : ");
                String identifiant = scanner.next();
                System.out.print("Adresse : ");
                String adresse = scanner.next();
                System.out.print("Type (étudiant, enseignant, visiteur) : ");
                TypeAdherent type = TypeAdherent.valueOf(scanner.next());
                // Appeler la méthode ajouterAdherent() du gestionnaire
                gestionnaire.ajouterAdherent(new Adherent(nom, prenom, adresse, identifiant, type));
                break;
            case 2:
                // Supprimer un adhérent
                System.out.println("Suppression d'un adhérent :");
                System.out.print("Identifiant de l'adhérent à supprimer : ");
                String idSupprimer = scanner.next();
                // Appeler la méthode supprimerAdherent() du gestionnaire
                gestionnaire.retirerAdherent(idSupprimer);
                break;
            case 3:
                // Modifier un adhérent
                System.out.println("Modification d'un adhérent :");
                System.out.print("Identifiant de l'adhérent à modifier : ");
                String idModifier = scanner.next();
                // Vérifier si l'adhérent existe
                if (gestionnaire.adherentExiste(idModifier)) {
                    // Demander les nouvelles informations
                    System.out.print("Nouveau nom : ");
                    String nouveauNom = scanner.next();
                    System.out.print("Nouveau prénom : ");
                    String nouveauPrenom = scanner.next();
                    System.out.print("Nouvelle adresse : ");
                    String nouvelleAdresse = scanner.next();
                    System.out.print("Nouveau type (étudiant, enseignant, visiteur) : ");
                    TypeAdherent nouveauType = TypeAdherent.valueOf(scanner.next());
                    // Créer un nouvel adhérent avec les nouvelles informations
                    Adherent nouveauAdherent = new Adherent(nouveauNom, nouveauPrenom, nouvelleAdresse, idModifier, nouveauType);
                    // Appeler la méthode modifierAdherent() du gestionnaire
                    gestionnaire.modifierAdherent(idModifier, nouveauAdherent);
                } else {
                    System.out.println("L'adhérent avec l'identifiant spécifié n'existe pas !");
                }
                break;
            case 4:
                // Rechercher des adhérents par type
                System.out.println("Recherche des adhérents par type :");
                System.out.print("Type d'adhérent à rechercher (étudiant, enseignant, visiteur) : ");
                TypeAdherent typeRecherche = TypeAdherent.valueOf(scanner.next());
                // Appeler la méthode rechercherAdherentsParType() du gestionnaire
                List<Adherent> adherentsParType = gestionnaire.rechercheAdherentParType(typeRecherche);
                // Afficher les adhérents trouvés
                if (!adherentsParType.isEmpty()) {
                    System.out.println("Adhérents trouvés :");
                    for (Adherent adherent : adherentsParType) {
                        System.out.println(adherent);
                    }
                } else {
                    System.out.println("Aucun adhérent trouvé pour ce type.");
                }
                break;
            case 5:
                // Rechercher un adhérent par identifiant
                System.out.print("Identifiant de l'adhérent à rechercher : ");
                String identifiantRecherche = scanner.next();
                Adherent adherentTrouve = gestionnaire.rechercherAdherentParIdentifiant(identifiantRecherche);
                if (adherentTrouve != null) {
                    System.out.println("Adhérent trouvé : " + adherentTrouve);
                } else {
                    System.out.println("Aucun adhérent trouvé avec cet identifiant.");
                }
                break;
            case 6:
                // Afficher la liste de tous les adhérents
                System.out.println("Liste de tous les adhérents :");
                gestionnaire.afficherListeAdherents();
                break;
            case 0:
                afficherMenuPrincipal();
                break;
            default:
                System.out.println("Choix invalide !");
        }
    }

    public void afficherMenuEmprunts() {
        System.out.println("Menu Emprunts :");
        System.out.println("1. Afficher la liste des emprunts en cours");
        System.out.println("2. Afficher la liste des emprunts passés");
        System.out.println("3. Afficher la liste des retardataires");
        System.out.println("0. Retour au menu principal");
        System.out.print("Choix : ");
        int choix = scanner.nextInt();

        switch (choix) {
            case 1:
                gestionnaire.getEmpruntsEnCours();
                break;
            case 2:
                gestionnaire.getEmpruntsPasses();
                break;
            case 3:
                gestionnaire.getRetardataires();
                break;
            case 0:
                afficherMenuPrincipal();
                break;
            default:
                System.out.println("Choix invalide !");
        }
    }



    public void afficherMenuDocuments() {
        System.out.println("Menu Documents :");
        System.out.println("1. Ajouter un document");
        System.out.println("2. Supprimer un document");
        System.out.println("3. Modifier un document");
        System.out.println("4. Rechercher un document par type");
        System.out.println("4. Rechercher un document avec son code Unique");
        System.out.println("4. Afficher la liste des documents");
        System.out.println("0. Retour au menu principal");
        System.out.print("Choix : ");
        int choix = scanner.nextInt();

        switch (choix) {
            case 1:
                // Ajouter un document
                System.out.println("Ajout d'un nouveau document :");
                System.out.print("Titre : ");
                String titre = scanner.next();
                System.out.print("Localisation : ");
                String localisation = scanner.next();
                System.out.print("Code unique : ");
                String codeUnique = scanner.next();
                System.out.print("Nombre d'exemplaires : ");
                int nombreExemplaires = scanner.nextInt();
                System.out.print("Type de document (livre, article, mémoire, magazine) : ");
                String typeDocument = scanner.next();
                // Demander les informations supplémentaires en fonction du type de document
                switch (typeDocument) {
                    case "livre":
                        System.out.print("Auteur : ");
                        String auteurLivre = scanner.next();
                        System.out.print("Éditeur : ");
                        String editeur = scanner.next();
                        System.out.print("Date d'édition (format : dd/MM/yyyy) : ");
                        String dateEdition = scanner.next();
                        LocalDate dateEditionLivre = LocalDate.parse(dateEdition, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        // Appeler la méthode ajouterDocument() du gestionnaire avec un nouveau livre
                        gestionnaire.ajouterDocument(new Livre(titre, localisation, codeUnique, nombreExemplaires, auteurLivre, editeur, dateEditionLivre));
                        break;
                    case "article":
                        System.out.print("Auteur : ");
                        String auteur = scanner.next();
                        System.out.print("Date de publication (format : dd/MM/yyyy) : ");
                        String datePublication = scanner.next();
                        LocalDate datePublicationArticle = LocalDate.parse(datePublication, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        // Appeler la méthode ajouterDocument() du gestionnaire avec un nouveau mémoire
                        gestionnaire.ajouterDocument(new Article(titre, localisation, codeUnique, nombreExemplaires, auteur, datePublicationArticle));
                        break;
                    case "mémoire":
                        System.out.print("Candidat : ");
                        String candidatMemoire = scanner.next();
                        System.out.print("Date de soutenance (format : dd/MM/yyyy) : ");
                        String dateSoutenance = scanner.next();
                        LocalDate dateSoutenanceMemoire = LocalDate.parse(dateSoutenance, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        // Appeler la méthode ajouterDocument() du gestionnaire avec un nouveau mémoire
                        gestionnaire.ajouterDocument(new Memoire(titre, localisation, codeUnique, nombreExemplaires, candidatMemoire, dateSoutenanceMemoire));
                        break;
                    case "magazine":
                        System.out.print("Frequence de Parution : ");
                        String frequenceParution = scanner.next();
                        gestionnaire.ajouterDocument(new Magazine(titre, localisation, codeUnique, nombreExemplaires, frequenceParution));
                        break;
                    default:
                        System.out.println("Type de document invalide !");
                }
                break;
            case 2:
                // Supprimer un document
                System.out.println("Suppression d'un document :");
                System.out.print("Code unique du document à supprimer : ");
                String codeUniqueDocument = scanner.next();
                // Appeler la méthode supprimerDocument() du gestionnaire
                gestionnaire.retirerDocument(codeUniqueDocument);
                break;
            case 3:
                // Modifier un document
                System.out.println("Modification d'un document :");
                System.out.print("Code unique du document à modifier : ");
                String codeUniqueDoc = scanner.next();
                // Vérifier si le document existe
                if (gestionnaire.documentExiste(codeUniqueDoc)) {
                    // Demander les nouvelles informations
                    System.out.print("Nouveau titre : ");
                    String nouveauTitre = scanner.next();
                    System.out.print("Nouvelle localisation : ");
                    String nouvelleLocalisation = scanner.next();
                    // Appeler la méthode modifierDocument() du gestionnaire
                    gestionnaire.modifierDocument(codeUniqueDoc, nouveauTitre, nouvelleLocalisation);
                } else {
                    System.out.println("Le document avec le code unique spécifié n'existe pas !");
                }
                break;
            case 4:
                // Rechercher des documents par type
                System.out.println("Recherche des documents par type :");
                System.out.print("Type de document à rechercher (livre, article, mémoire, magazine) : ");
                TypeDocument typeDocumentRecherche = TypeDocument.valueOf(scanner.next());
                // Appeler la méthode rechercherDocumentsParType() du gestionnaire
                List<Document> documentsParType = gestionnaire.rechercherDocumentsParType(typeDocumentRecherche);
                // Afficher les documents trouvés
                if (!documentsParType.isEmpty()) {
                    System.out.println("Documents trouvés :");
                    for (Document document : documentsParType) {
                        System.out.println(document);
                    }
                } else {
                    System.out.println("Aucun document trouvé pour ce type.");
                }
                break;
            case 5:
                // Rechercher un document par identifiant
                System.out.print("Identifiant du document à rechercher : ");
                String codeUniqueRecherche = scanner.next();
                Document documentTrouve = gestionnaire.rechercherDocumentParIdentifiant(codeUniqueRecherche);
                if (documentTrouve != null) {
                    System.out.println("Document trouvé : " + documentTrouve);
                } else {
                    System.out.println("Aucun document trouvé avec cet identifiant.");
                }
                break;
            case 6:
                // Afficher la liste de tous les documents
                System.out.println("Liste de tous les documents :");
                gestionnaire.afficherListeDocuments();
                break;
            case 0:
                afficherMenuPrincipal();
                break;
            default:
                System.out.println("Choix invalide !");
        }

    }
}
