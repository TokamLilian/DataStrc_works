package Network;

public class ReseauBST {
    private Node[] network;
    private static int networkSize;

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
            userId = networkSize++;
        }

        public void addFriend(String name) {
            if (numFriends < maxFriends){
                friends[numFriends] = name;
                numFriends ++;
            }
        }
    }

    public void ajoute_nouvel_utilisateur(ReseauBST tree, int id, String name){
        Node user = new Node(id);
        user.userName = name;

        Node [] network = tree.getNetwork();

        network[networkSize] = user;
        networkSize++;
    }

    public Node getUserById(int id){
        for (int i = 0 ; i < networkSize ; i++){
            if (network[i].userId == id) return network[i];
        }
        return null;
    }
    

    public Node [] getNetwork(){
        return network;
    }

    public int compte_amis(ReseauBST tree, int id){
        Node[] users = tree.getNetwork();
        Node user = users[id];
        String [] friends = user.friends;
        return friends.length;
    }

    public void ami_tout_le_monde(ReseauBST tree, String name){
        for (Node user : tree.getNetwork()){
            user.addFriend(name);
        }

    }

    public String[] liste_nom_dans_ordre(ReseauBST tree){
        Node[] network = tree.getNetwork();
        String[] userNames = new String[network.length];
        int position = 0;
        for (Node user : network){
            userNames[position] = user.userName;
        }

        return userNames;

    }

    private boolean isFriend(String userName, Node currentUser) {
        for (String friend :  currentUser.friends) {
            if (friend.equalsIgnoreCase(userName)) {
                return true;
            }
        }
        return false;
    }

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

    public String[] recommender(ReseauBST tree, int id){
        Node[] network = tree.getNetwork();
        Node currentUser = getUserById(id);
        
        String[] recomendations = new String[network.length];
        int recomendationsSize = 0;
        
        for (Node user : network){
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


