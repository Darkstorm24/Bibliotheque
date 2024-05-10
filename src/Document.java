public abstract class Document {
    protected String titre;
    private String localisation;
    private String codeUnique;
    private int nombreExemplaires;
    private TypeDocument typeDocument;

    public Document(String titre, String localisation, String codeUnique, int nombreExemplaires) {
        this.titre = titre;
        this.localisation = localisation;
        this.codeUnique = codeUnique;
        this.nombreExemplaires = nombreExemplaires;

    }


    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getLocalisation() {
        return localisation;
    }
    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }
    public String getCodeUnique() {
        return codeUnique;
    }
    public void setCodeUnique(String  codeUnique) {
        this.codeUnique = codeUnique;
    }
    public int getNombreExemplaires() {
        return nombreExemplaires;
    }
    public void setNombreExemplaires(int nombreExemplaires) {
        this.nombreExemplaires = nombreExemplaires;
    }

    public TypeDocument getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(TypeDocument typeDocument) {
        this.typeDocument = typeDocument;
    }
}
