public class Main {

    public static void main(String[] args) {
        // Instanciation des objets nécessaires
        GestionnaireBibliotheque gestionnaire = new GestionnaireBibliotheque();
        Menu menu = new Menu(gestionnaire);

        // Logique de démarrage de l'application
        menu.afficherMenuPrincipal();
    }
}
