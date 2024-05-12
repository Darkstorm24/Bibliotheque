public class Magazine extends Document{
    private String frequnceParution;

    public Magazine(String titre, String localisation, String codeUnique, int nombreExemplaires, String frequnceParution) {
        super(titre, localisation, codeUnique, nombreExemplaires);
        this.frequnceParution = frequnceParution;
    }

    public String getFrequnceParution() {
        return frequnceParution;
    }
    public void setFrequnceParution(String frequnceParution) {
        this.frequnceParution = frequnceParution;
    }
}
