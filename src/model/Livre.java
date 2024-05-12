import java.time.LocalDate;
import java.util.Date;

public class Livre extends Document{
    private String auteur;
    private String editeur;
    private LocalDate dateEdition;

    public Livre(String titre, String localisation, String codeUnique, int nombreExemplaires,String auteur, String editeur, LocalDate dateEdition) {
        super(titre, localisation, codeUnique, nombreExemplaires);
        this.auteur = auteur;
        this.editeur = editeur;
        this.dateEdition = dateEdition;
    }

    public String getAuteur() {
        return auteur;
    }
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    public String getEditeur() {
        return editeur;
    }
    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }
    public LocalDate getDateEdition() {
        return dateEdition;
    }
    public void setDateEdition(LocalDate dateEdition) {
        this.dateEdition = dateEdition;
    }
}