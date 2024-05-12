import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Adherent extends Personne {
    private String id;
    private TypeAdherent type;
    private int nombreDocuments;    //nombre de documents empruntables
    private int duree; //la duree en semaines
    private int nombreEmpruntsEffectues;
    private int nombreEmpruntsEnCours;
    private int nombreEmpruntsDepasses;
    private boolean status;
    private List<Emprunt> emprunts;





    public Adherent(String nom, String prenom, String adresse, String id, TypeAdherent type) {
        super(nom, prenom, adresse);
        this.id = id;
        this.type = type;
        this.nombreDocuments = getNombreDocuments();
        this.duree = getDuree();
        this.nombreEmpruntsEffectues = 0;
        this.nombreEmpruntsEnCours = 0;
        this.nombreEmpruntsDepasses = 0;
        this.emprunts = new ArrayList<>();

    }

    //Accesseurs

    public String getId() {
        return id;
    }

    public TypeAdherent getType() {
        return type;
    }

    public int getNombreDocuments() {
        return switch (type) {
            case Etudiant -> nombreDocuments = 2;
            case Enseignant -> nombreDocuments = 4;
            case Visiteur -> nombreDocuments = 1;
            default -> 0;
        };
    }

    public int getDuree() {
        return switch (type) {
            case Etudiant -> duree = 1;
            case Enseignant -> duree = 3;
            case Visiteur -> duree = 1;
            default -> 0;
        };
    }

    public boolean getStatus(){
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(TypeAdherent type) {
        if (type != null) {
            this.type = type;
        } else {
            System.out.println("Le type d'adhérent ne peut pas être nul.");
        }
    }

    public void setNombreDocuments(int nombreDocuments) {
        this.nombreDocuments = nombreDocuments;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getNombreEmpruntsEffectues() {
        return nombreEmpruntsEffectues;
    }
    public int getNombreEmpruntsEnCours() {
        return nombreEmpruntsEnCours;
    }
    public int getNombreEmpruntsDepasses() {
        return nombreEmpruntsDepasses;
    }


    //Methodes de la classe


    //enregistrement d'un nouvel emprunt
    public void ajouterEmprunt(Emprunt emprunt) {
        emprunts.add(emprunt);
        nombreEmpruntsEffectues++;
        nombreEmpruntsEnCours++;
    }

    //enregistrement retour d'un emprunt
    public void retirerEmprunt(Emprunt emprunt) {
        emprunts.remove(emprunt);
        nombreEmpruntsEnCours--;
        if(emprunt.estEnRetard()){
            nombreEmpruntsDepasses++;
        }
    }

    //methode pour mettre a jour le nombre d'emprunts depasses
    public void mettreAJourEmpruntDepassea() {
        for (Emprunt emprunt : emprunts) {
            if(emprunt.estEnRetard()){
                nombreEmpruntsDepasses++;
            }
        }
    }

    //methode pour savoir si l'adherent peut emprunter
    public boolean peutEmprunter() {
        switch (type) {
            case Etudiant :
                return nombreEmpruntsEnCours < 2;
            case Enseignant:
                return nombreEmpruntsEnCours < 4;
            case Visiteur:
                return nombreEmpruntsEnCours < 1;
            default:
                return false;
        }

    }

    public LocalDate calculerDateLimite(Document document) {
        // Récupérer le type de l'adhérent
        TypeAdherent typeAdherent = this.getType();

        // Déterminer la durée de prêt en fonction du type de l'adhérent
        int dureePret = 0;
        switch (typeAdherent) {
            case Etudiant:
                dureePret = 7; // Durée de prêt pour un étudiant : 7 jours
                break;
            case Enseignant:
                dureePret = 21; // Durée de prêt pour un enseignant : 21 jours
                break;
            case Visiteur:
                dureePret = 7; // Durée de prêt pour un visiteur : 7 jours
                break;
            default:
                // Gérer le cas par défaut si nécessaire
                break;
        }
        // Calculer la date limite en ajoutant la durée de prêt à la date actuelle
        return LocalDate.now().plusDays(dureePret);
    }

}
