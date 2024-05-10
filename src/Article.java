import java.time.LocalDate;
import java.util.Date;

public class Article extends Document{
    private String author;  //nom de l'auteur
    private LocalDate datePublication;

    public Article(String titre, String localisation, String codeUnique, int nombreExemplaires, String author,  LocalDate datePublication) {
        super(titre, localisation, codeUnique, nombreExemplaires);
        this.author = author;
        this.datePublication = datePublication;
    }

    public String getAuthor() {
        return author;
    }
    public  LocalDate getDatePublication() {
        return datePublication;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setDatePublication( LocalDate datePublication) {
        this.datePublication = datePublication;
    }

}
