import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Memoire extends Document{
    private String candidat;  //nom du du candidat
    private LocalDate dateSoutenance;

    public Memoire(String titre, String localisation, String codeUnique, int nombreExemplaires,String candidat, LocalDate dateSoutenance) {
        super(titre, localisation, codeUnique, nombreExemplaires);
        this.candidat = candidat;
        this.dateSoutenance = dateSoutenance;
    }

    public String getCandidat() {
        return candidat;
    }
    public void setCandidat(String candidat) {
        this.candidat = candidat;
    }
    public LocalDate getDateSoutenance() {
        return dateSoutenance;
    }
    public void setDateSoutenance(LocalDate dateSoutenance) {
        this.dateSoutenance = dateSoutenance;
    }
}
