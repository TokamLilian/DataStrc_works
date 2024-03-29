package Network;

public class ReseauBST {
    private Node[] network;
    public static int networkSize;

    public ReseauBST(){
        network = new Node[100];
        networkSize = 0;
    }

    static class Node{
        public int userId;
        public String userName;
        public String[] friends;
        public int maxFriends = 10;
        public int numFriends = 0;
        public Node leftChild;

        public Node(int id) {
            userId = id;
            userName=null;
            friends = new String[maxFriends];
        }

        /* Ajoute un ami parmis les amis de l'utilisateur */
        public void addFriend(String name) {
            if (numFriends < maxFriends){
                friends[numFriends] = name;
                numFriends ++;
            }
        }
    }

    /* Ajoute un nouvel utilisateur au reseau */
    public void ajoute_nouvel_utilisateur(ReseauBST tree, int id, String name){
        Node user = new Node(id);
        user.userName = name;

        Node [] network = tree.getNetwork();
        int networkSize = tree.networkSize;

        network[networkSize] = user;
        this.networkSize++;
    }

    /* retourne l'utilisateur correspondant à id */
    public Node getUserById(int id){
        if (id<0 || id>=networkSize) return null;
        for (int i = 0 ; i < networkSize ; i++){
            if (network[i] == null) break;
            if (network[i].userId == id) return network[i];
        }
        return null;
    }

    /* retourne l'utilisateur correspondant au nom */
    public Node getUserByName(String name){
        for (int i = 0 ; i < networkSize ; i++){
            if (network[i] == null) break;
            if (network[i].userName == name) return network[i];
        }
        return null;
    }
    
    /* Retourne un tableau d'utilisateurs */
    public Node [] getNetwork(){
        return network;
    }

    /* Retourne le nombre d'amis de l'utilisateur */
    public int compte_amis(ReseauBST tree, int id){
        Node[] users = tree.getNetwork();
        if(tree.getUserById(id)==null) throw new IllegalArgumentException("User is not in network");
        Node user = users[id];
        return user.numFriends;
    }

    /* Ajout d'un ami a tout le monde dans le reseau*/
    public void ami_tout_le_monde(ReseauBST tree, String name){
        for (Node user : tree.getNetwork()){
            if(user == null) break;
            user.addFriend(name);
        }
        if (tree.getUserByName(name) == null) tree.ajoute_nouvel_utilisateur(tree, tree.networkSize, name);

    }

    /* retourne la liste de tous les utilisateurs du reseau */
    public String[] liste_nom_dans_ordre(ReseauBST tree){
        Node[] network = tree.getNetwork();
        String[] userNames = new String[network.length];
        int position = 0;
        for (Node user : network){
            if(user == null) break;
            userNames[position] = user.userName; position++;
        }

        return userNames;

    }

    /* retourne vrai si les deux utilisateurs sont amis */
    private boolean isFriend(String userName, Node currentUser) {
        for (String friend : currentUser.friends) {
            if(friend == null) break;
            if (friend.equalsIgnoreCase(userName)) {
                return true;
            }
        }
        return false;
    }

    /* Retourne vrai si les deux  utilisateurs ont des amis en commun */
    private boolean haveCommonFriends(Node userByName, Node currentUser) {
        boolean friendsInCommon = false;

        for (String friend : userByName.friends){
            if (isFriend(friend, currentUser)) {
                friendsInCommon = true;
                break;
            }

        }
        return friendsInCommon;
    }

    /* Retourne un tableau d'utilisateur qui ont un ami en commun avec le user passé en paramètre par son id */
    public String[] recommender(ReseauBST tree, int id){
        Node[] network = tree.getNetwork();
        Node currentUser = getUserById(id);
        
        String[] recomendations = new String[network.length];
        int recomendationsSize = 0;
        
        for (Node user : network){
            if(user == null) break;
            String userName = user.userName;
            if (user!=currentUser && !isFriend(userName, currentUser)){
                if(haveCommonFriends(user, currentUser)) {
                    recomendations[recomendationsSize] = userName;
                    recomendationsSize++;
                }
        }
        
    }
        return recomendations;
    }
}


