package Network;

public class Main {
    public static void main(String[] args){
        Node root = null;
        int tailleArbre = 5; // Taille de l'arbre
        int tailleAmis = 3; // Taille maximale de la liste d'amis
        ReseauBST socialNetwork = new ReseauBST();
        // Ajouter des utilisateurs
        socialNetwork.addUser(socialNetwork, 1, "Alice");
        socialNetwork.addUser(socialNetwork, 2, "Bob");
        socialNetwork.addUser(socialNetwork, 3, "Charlie");
        socialNetwork.addUser(socialNetwork, 4, "David");

        // Compter les amis
        System.out.println("Nombre d'amis de Alice: " + socialNetwork.compte_amis(socialNetwork, 1));

        // Ajouter un utilisateur à la liste d'amis de tout le monde
        socialNetwork.ami_de_tout_le_monde(socialNetwork, "Eve");

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
