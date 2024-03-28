package Tree;

public class Node {
    private int value;
    private int priority;
    private Node parent;
    private Node leftChild;
    private Node rightChild;

    public Node(){
        value = 0;
    }

    public int getValue(){
        return value;
    }

    
    public Node getParent(){
        try{
            return parent;
        }catch (Exception e){
            throw e;
        }
    }

    public Node getLeft(){
        return leftChild;
    }
    
    public Node getRight(){
        return rightChild;
    }

    public int getPriority() {
        return priority;
    }
    
    public int getDegree() {
        int degree = 0;
        // TODO: Implement this method
        return degree;
    }
    
    public int getNumChildren(){
        if (leftChild == null && rightChild == null)
            return 0;
            if (leftChild == null || rightChild == null)
            return 1;
        else
            return 2;
            
        }

    public void setParent(Node node){
        parent = node;
    }

    public void setPriority(int p){
        priority = p;
    }

    public void setValue(int v){
        value = v;
    }

    public void addChild(Node childNode){
        if (leftChild == null) leftChild = childNode;
        else if (rightChild == null) rightChild = childNode;
        else throw new IllegalStateException("Node has already two children");

    }
    
    public void removeChild(Node node){
        if (leftChild == node) leftChild = null;
        else if (rightChild == node) rightChild = null;
        else throw new IllegalArgumentException("Node is not a child of this parent node");
    }

    public int depth(){
        int depth = 0;
        Node n = this;

        while (n.getParent() != null){
            depth++;
            n = n.getParent();
        }
        return depth;
    }

    public boolean isLeaf(){
        //return (getNumChildren() == 0);
        return (leftChild == null && rightChild == null);
    }
    
    public boolean isFull(){
        //return (getNumChildren() == 2);
        return (leftChild != null && rightChild != null);
    }

    public int size() {
        return getNumChildren();
    }
}
