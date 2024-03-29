package Network;

public class ReseauBST {
    private Node[] network;
    private int networkSize;

    public ReseauBST(){
        network = new Node[100];
        networkSize = 0;
    }

    public void addUser(ReseauBST tree, int id, String name){
        Node user = new Node(id);
        user.setUserName(name);

        Node [] network = tree.getNetwork();

        network[networkSize] = user;
        networkSize++;
    }

    public Node getUserById(int id){
        return null;
    }
    
    public Node getUserByName(String name){
        return null;
    }

    public Node [] getNetwork(){
        return network;
    }

    public int compte_amis(ReseauBST tree, int id){
        Node[] users = tree.getNetwork();
        Node user = users[id];
        String [] friends = user.getFriends();
        return friends.length;
    }

    public void ami_de_tout_le_monde(ReseauBST tree, String name){
        for (Node user : tree.getNetwork()){
            user.addFriend(name);
        }

    }

    public String[] liste_nom_dans_ordre(ReseauBST tree){
        Node[] network = tree.getNetwork();
        String[] userNames = new String[network.length];
        int position = 0;
        for (Node user : network){
            userNames[position] = user.getUserName();
        }

        return userNames;

    }

    public String[] recommender(ReseauBST tree, int id){
        Node[] network = tree.getNetwork();
        Node currentUser = getUserById(id);
        String[] users = liste_nom_dans_ordre(tree);
        String[] recomendations = new String[network.length];
        
        for (String userName : users){
            // if (!userName in currentUser.getFriends){
                //
            //}
        }

        return recomendations;

    }
}
