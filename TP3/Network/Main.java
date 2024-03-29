package Network;

public class Main {
    public static void main(String[] args){
        ReseauBST socialNetwork = new ReseauBST();
        // Ajouter des utilisateurs
        socialNetwork.ajoute_nouvel_utilisateur(socialNetwork, 1, "Alice");
        socialNetwork.ajoute_nouvel_utilisateur(socialNetwork, 2, "Bob");
        socialNetwork.ajoute_nouvel_utilisateur(socialNetwork, 3, "Charlie");
        socialNetwork.ajoute_nouvel_utilisateur(socialNetwork, 4, "David");

        // Compter les amis
        System.out.println("Nombre d'amis de Alice: " + socialNetwork.compte_amis(socialNetwork, 1));

        // Ajouter un utilisateur à la liste d'amis de tout le monde
        socialNetwork.ami_tout_le_monde(socialNetwork, "Eve");

        // Liste des noms dans l'ordre
        String[] noms = socialNetwork.liste_nom_dans_ordre(socialNetwork);
        System.out.print("Noms dans l'ordre: ");
        for (String nom : noms) {
            if (nom != null) {
                System.out.print(nom + " ");
            }
        }
        System.out.println();

        // Recommandations d'amis
        String[] recommandations = socialNetwork.recommender(socialNetwork, 2);
        System.out.print("Recommandations d'amis pour Alice: ");
        for (String rec : recommandations) {
            if (rec != null) {
                System.out.print(rec + " ");
            }
        }
    }
}
