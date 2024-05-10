import java.time.LocalDate;
import java.util.Date;

public class Emprunt {
    private Adherent adherent;
    private Document document;
    private LocalDate debutPret;
    private LocalDate dateLimite; //date limit de restauration

    public Emprunt(Adherent adherent,Document document, LocalDate debutPret, LocalDate dateLimite) {
        this.adherent = adherent;
        this.document = document;
        this.debutPret = debutPret;
        this.dateLimite = dateLimite;

    }

    //Acceesseurs
    public Adherent getAdherent() {
        return adherent;
    }
    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }
    public Document getDocument() {
        return document;
    }
    public void setDocument(Document document) {
        this.document = document;
    }
    public LocalDate getDebutPret() {
        return debutPret;
    }
    public void setDebutPret(LocalDate debutPret) {
        this.debutPret = debutPret;
    }
    public LocalDate getFinPret() {
        return dateLimite;
    }
    public void setFinPret(LocalDate finPret) {
        this.dateLimite = finPret;
    }


    //Methodes de la classe emprunt

    public boolean estEnCours() {
        LocalDate aujourdhui = LocalDate.now();
        return !aujourdhui.isAfter(dateLimite);
    }

    public boolean estPasse() {
        LocalDate aujourdhui = LocalDate.now();
        return aujourdhui.isAfter(dateLimite);
    }

    public boolean estEnRetard() {
        LocalDate aujourdhui = LocalDate.now();
        return aujourdhui.isAfter(dateLimite) && !aujourdhui.isAfter(dateLimite.plusDays(1)); // Au cas où la date limite est le jour même
    }


}
