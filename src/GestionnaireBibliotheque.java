import java.time.LocalDate;
import java.util.*;

public class GestionnaireBibliotheque {
    private Map<String, Adherent> adherents;
    private Map<String, Document> documents;
    private Map<Document, Emprunt> emprunts;

    public GestionnaireBibliotheque() {
        adherents = new HashMap<>();
        documents = new HashMap<>();
        emprunts = new HashMap<>();
    }
    //Ajouter un document
    public void ajouterDocument(Document document) {
        documents.put(document.getCodeUnique(), document);
    }

    //Supprimer un document
    public void retirerDocument(String codeUnique) {
        documents.remove(codeUnique);
    }


    //Ajout d'un adherent
    public void ajouterAdherent(Adherent adherent) {
        adherents.put(adherent.getId(), adherent);
    }

    //Suppresion d'un adherent
    public void retirerAdherent(String id) {
        adherents.remove(id);
    }

    //Modification des informations d'un adherent
    public void modifierAdherent(String id, Adherent nouveauAdherent) {
        if (adherents.containsKey(id)) {
            adherents.put(id, nouveauAdherent);
        } else {
            System.out.println("L'adherent n'existe pas");
        }
    }

    //Modification des informations d'un document
    public void modifierDocument(String codeUnique, String nouveauTitre, String nouvelleLocalisation) {
        if (documents.containsKey(codeUnique)) {
            Document document = documents.get(codeUnique);
            document.setTitre(nouveauTitre);
            document.setLocalisation(nouvelleLocalisation);
            System.out.println("Document modifié avec succès !");
        } else {
            System.out.println("Le document avec le code unique spécifié n'existe pas !");
        }
    }

    //Verifier si l'adherent existe
    public boolean adherentExiste(String id) {
        return adherents.containsKey(id);
    }

    //Verifier si le document exist
    public boolean documentExiste(String codeUnique) {
        return documents.containsKey(codeUnique);
    }

    //Recherche d'un adherent par son identifiant
    public Adherent rechercherAdherentParIdentifiant(String identifiant) {
        for (Adherent adherent : adherents.values()) {
            if (adherent.getId().equals(identifiant)) {
                return adherent;
            }
        }
        return null; // Retourner null si aucun adhérent avec cet identifiant n'est trouvé
    }

    //Recherche des documents avec leur code unique
    public Document rechercherDocumentParIdentifiant(String codeUnique) {
        return documents.get(codeUnique);
    }

    //Recherche des adherents par type
    public List<Adherent> rechercheAdherentParType(TypeAdherent type){
        List<Adherent> adherentsTrouves = new ArrayList<>();
        for (Adherent adherent : adherents.values()){
         if(adherent.getType().equals(type)) {
             adherentsTrouves.add(adherent);
         }
        }
        return adherentsTrouves;
    }

    //Recherche des documents par type
    public List<Document> rechercherDocumentsParType(TypeDocument type) {
        List<Document> documentsTrouves = new ArrayList<>();
        for (Document document : documents.values()) {
            if (document.getTypeDocument().equals(type)) {
                documentsTrouves.add(document);
            }
        }
        return documentsTrouves;
    }


    //Gestion des retardataires
    public void retardataires(){
        Date maintenant = new Date();
        for(Emprunt emprunt : emprunts.values()){
            if(emprunt.estEnRetard()){
                Adherent adherent = emprunt.getAdherent();
                adherent.setStatus(true);
            }
        }
    }

    //emprunter un document
    public void emprunterDocument(Adherent adherent, Document document) {
        if(emprunts.containsKey(document)){
            System.out.println("Ce document a deja ete emprunte.");
            return;
        }
        if(adherent.peutEmprunter() && (!adherent.getStatus())){
            LocalDate dateDebut = LocalDate.now();
            LocalDate dateLimite = adherent.calculerDateLimite(document);
            Emprunt emprunt = new Emprunt(adherent, document, dateDebut, dateLimite);
            emprunts.put(document, emprunt);
            adherent.ajouterEmprunt(emprunt);
        } else{
            System.out.println("Vous avez atteint votre limite maximal d'emprunts.");
        }
    }

    //Retourner un document
    public void retournerDocument( Document document) {
        if(emprunts.containsKey(document)){
            Emprunt emprunt = emprunts.get(document);
            emprunts.remove(document);
            Adherent adherent = emprunt.getAdherent();
            adherent.retirerEmprunt(emprunt);
        }else {
            System.out.println("Le document n'est pas actuellement emprunte.");
        }
    }

    //Liste des adherents
    public void afficherListeAdherents() {
        for (Adherent adherent : adherents.values()) {
            System.out.println(adherent);
        }
    }

    //liste des documents
    public void afficherListeDocuments() {
        System.out.println("Liste de tous les documents :");
        for (Document document : documents.values()) {
            System.out.println(document);
        }
    }

    //Liste des emprunts en cours
    public List<Emprunt> getEmpruntsEnCours() {
        List<Emprunt> empruntsEnCours = new ArrayList<>();
        for (Emprunt emprunt : emprunts.values()) {
            if (emprunt.estEnCours()) {
                empruntsEnCours.add(emprunt);
            }
        }
        return empruntsEnCours;
    }

    //Liste des emprunts passes
    public List<Emprunt> getEmpruntsPasses() {
        List<Emprunt> empruntsPasses = new ArrayList<>();
        for (Emprunt emprunt : emprunts.values()) {
            if (emprunt.estPasse()) {
                empruntsPasses.add(emprunt);
            }
        }
        return empruntsPasses;
    }

    //Liste retardataires
    public List<Adherent> getRetardataires() {
        List<Adherent> retardataires = new ArrayList<>();
        for (Emprunt emprunt : emprunts.values()) {
            if (emprunt.estEnRetard()) {
                retardataires.add(emprunt.getAdherent());
            }
        }
        return retardataires;
    }
}


