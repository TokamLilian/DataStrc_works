package Network;

public class Node {
    private int userId = -1;
    private String userName;
    private String[] friends;
    private int numFriends = 0;
    private Node leftChild;

    public Node(int id){
        userId = id;
        userName = null;
        friends = new String[10];
    }

    public int getUserId(){
        return userId;
    }


    public Node getLeft(){
        return leftChild;
    }

    public String[] getFriends() {
        return friends;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String name){
        userName = name;
    }

    public void addFriend(String newFriend){
        try {
            friends[numFriends] = newFriend;
            numFriends++;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void addChild(Node childNode){
        if (leftChild == null) leftChild = childNode;
        else throw new IllegalStateException("Node has already a child");
    }
    
    public void removeChild(Node node){
        if (leftChild == node) leftChild = null;
        else throw new IllegalArgumentException("Node is not a child of this parent node");
    }
    
}
