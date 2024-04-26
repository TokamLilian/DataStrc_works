package graph;


import graph.doublyLinkedList.DoublyLinkedList;
import graph.doublyLinkedList.Node;
import graph.doublyLinkedList.NodeIterator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Graph <E,T> {
	

	private DoublyLinkedList<Vertex <E,T>> vertexList;
	private DoublyLinkedList<Edge<E,T>> edgeList;

	private boolean directed;
	private boolean isCyclic;
	private boolean isConnected;
	private int connectedComponents;
	
	private int unique_id = 0;
	
	
	public Graph(boolean directed) {
		vertexList = new DoublyLinkedList<Vertex<E,T>>();
		edgeList = new DoublyLinkedList<Edge<E,T>>();
		this.directed = directed;
	}
	

	private Vertex<E,T> addVertex(E data, int id){
		Vertex<E,T> vertex = new Vertex<E,T>(data, id);
		Node<Vertex<E,T>> node = vertexList.add(vertex);
		vertex.setPosition(node);
		return vertex;
	}
	public Vertex<E,T> addVertex(E data){
		return addVertex(data, unique_id++);
	}
	

	public NodeIterator<Vertex<E,T>> vertices() {
		return vertexList.iterator();
	}


	public NodeIterator<Edge<E,T>> edges() {
		return edgeList.iterator();
	}
	

	public Vertex<E,T>[] vertices_array(){
		Vertex<E,T>[] tmp = new Vertex[vertexList.size()];
		NodeIterator<Vertex<E,T>> iter = vertices();
		int index = 0;
		while(iter.hasNext())
			tmp[index++] = iter.next();
		return tmp;
	}
	

	public Edge<E,T>[] edges_array(){
		Edge<E,T>[] tmp = new Edge[edgeList.size()];
		NodeIterator<Edge<E,T>> iter = edges();
		int index = 0;
		while(iter.hasNext())
			tmp[index++] = iter.next();
		return tmp;
	}

	public String toString(){
		String output = "Vertices:\n";
		for(Vertex<E,T> v : vertices_array())
			output += String.format("%s ", v.toString());
		
		output += "\n\nEdges:\n";
		
		for(Edge<E,T> e : edges_array()){
			output += String.format("%s\n", e.toString());
		}
		return output;
	}
	
	public static Graph<String,String> inParser (String fileName, boolean directed) throws FileNotFoundException{
		Graph<String,String> graph = new Graph<String,String>(directed);
		
		Scanner scan = new Scanner(new File(fileName));
		String readLine;
		Pattern pattern;
		Matcher matcher;
		
		readLine = scan.nextLine();
		pattern = Pattern.compile("size\\s*=\\s*(\\d+)");
		matcher = pattern.matcher(readLine);
		matcher.find();
		Vertex<String,String> vertices[] = new Vertex[Integer.parseInt(matcher.group(1))];
		
		while(!(readLine = scan.nextLine()).equals(";") ){
			pattern = Pattern.compile("([^0-9]*)\\s*(\\d+)\\s*=\\s*(.*)");
			matcher = pattern.matcher(readLine);
			matcher.find();
			if(matcher.group(1) == null || matcher.group(1).isEmpty()){
				vertices[Integer.parseInt(matcher.group(2))] = graph.addVertex(matcher.group(3));
			}else if(matcher.group(1).trim().equals("//") || matcher.group(1).trim().equals("#")){
				continue;
			}else{
				throw new InputMismatchException();
			}
		}
		
		while(!(readLine = scan.nextLine()).equals(";") ){
			pattern = Pattern.compile("(.*)\\s*\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*(,\\s*(\\d+|\\d+\\.\\d+)\\s*)?\\)(\\s*=\\s*(.*))?");
			matcher = pattern.matcher(readLine);
			matcher.find();
			if(matcher.group(1) == null || matcher.group(1).isEmpty()){
				double weight = 0.0;
				int v1Index = Integer.parseInt(matcher.group(2));
				int v2Index = Integer.parseInt(matcher.group(3));
				if(matcher.group(5) != null)
					weight = Double.parseDouble(matcher.group(5));
				String label = matcher.group(7);
				
				graph.addEdge(vertices[v1Index], vertices[v2Index], label, weight);
			}else if(matcher.group(1).trim().equals("//") || matcher.group(1).trim().equals("#")){
				continue;
			}else{
				throw new InputMismatchException();
			}
		}
		return graph;
	}

	/**
	 * Add an edge between two vertices and return the created edge
	 * @param vertex
	 * @param vertex2
	 * @param label
	 * @param weight
	 * @return
	 */
	private Edge<E,T> addEdge(Vertex<E, T> vertex, Vertex<E, T> vertex2, String label, double weight) {

		Edge<E,T> newEdge = new Edge(vertex, vertex2);
		newEdge.setLabel((T) label);
		newEdge.setWeight(weight);
		
		Node<Edge<E,T>> node = edgeList.add(newEdge);
		newEdge.setPosition(node);
		return newEdge;
	}

	/** Delete an edge*/
	public void removeEdge(Vertex<E, T> vertex, Vertex<E, T> vertex2){
		Node<Edge<E, T>> edgeToBeDeleted = null;
		
		NodeIterator<Edge<E, T>> NodeIterator = vertex.getOutEdges();
		Edge<E,T> edge;
		while (NodeIterator.hasNext()) {
			edge = NodeIterator.next();
			if(edge.getV2() == vertex2) edgeToBeDeleted = edge.getPosition();
			
		}

		vertex.removeOutEdge(edgeToBeDeleted);
		vertex2.removeInEdge(edgeToBeDeleted);
		edgeList.remove(edgeToBeDeleted);

	}

	/** Delete a vertex v and all its corresponding edges  */
	public void removeVertex(Vertex<E, T> vertex){
		NodeIterator<Edge<E, T>> InNodeIterator = vertex.getInEdges();
		Edge<E,T> edge;
		while (InNodeIterator.hasNext()) {
			edge = InNodeIterator.next();
			edge.getV1().removeOutEdge(edge.getPosition());
			vertex.removeInEdge(edge.getPosition());
		}

		NodeIterator<Edge<E, T>> OutNodeIterator = vertex.getOutEdges();
		while (OutNodeIterator.hasNext()) {
			edge = OutNodeIterator.next();
			vertex.removeOutEdge(edge.getPosition());
			edge.getV2().removeInEdge(edge.getPosition());
		}
		
		vertexList.remove(vertex.getPosition());

	}

	/** Checks if two vetices are adjacent  */
	public boolean areAdjacent(Vertex<E, T> v1, Vertex<E, T> v2){
		NodeIterator<Edge<E, T>> InNodeIterator = v1.getInEdges();
		Edge<E,T> edge;
		while (InNodeIterator.hasNext()) {
			edge = InNodeIterator.next();
			if (edge.getV1() == v2);
				return true;
		}

		NodeIterator<Edge<E, T>> OutNodeIterator = v1.getOutEdges();
		while (OutNodeIterator.hasNext()) {
			edge = OutNodeIterator.next();
			
			if (edge.getV2() == v2){
				return true;
			}
		}

		return false;

	}
	
	/**
	 * Verify if the graph is connected
	 * @return
	 */	
	public boolean isConnected() {
		// Perform DFS traversal
		Vertex<E, T>[] verticesArray = vertices_array();
        DFS();		// Perform a DFS to mark all reachable vertices

        // Check if all vertices were visited
        for (Vertex<E,T> vertex : verticesArray) {
            if (vertex.getStatus() == 0) {
                return false; // Graph is disconnected
            }
        }
		isConnected = true;
        return isConnected; // Graph is connected
	}

	/**
	 * Verify if the graph is directed
	 * @return
	 */
    public boolean isDirected() {
        return directed;
    }
	
	public boolean isCyclic(Vertex<E, T> vertex) {
		if (vertex.getStatus() == 2) return true;			// return true as we come over an already visited node
        DoublyLinkedList<Vertex<E, T>> dfs = new DoublyLinkedList<>();
		vertex.setStatus(2);
		dfs.add(vertex);
		
        Vertex<E, T>[] neighbours = vertex.getNeighbors();

		for (Vertex<E, T> next : neighbours){
			isCyclic(next);
		} //move to next vertex

		return false;
    }
	
	/**
	 * Verify if the graph is cyclic
	 * @return
	 */
	public boolean isCyclic() {
		Vertex<E,T>[] vertices = vertices_array();
		for (Vertex<E,T> v : vertices)
			if (isCyclic(v)){ 
				isCyclic = true;
				return isCyclic;
			}

		return false;
	}

	/**
	 * Verifies the number of connected components in the graph
	 * @return Number of connected vertices
	 */
    public int connectedComponents() {
        int count = 0;
		DoublyLinkedList<Vertex<E, T>> dfs_list = new DoublyLinkedList<>();

		@SuppressWarnings("unchecked")
		Vertex<E, T>[] verticesArray = vertices_array();
		for (Vertex<E, T> vertex : verticesArray){
			if (vertex.getStatus() == 0){			// if not visited
				count++;
				DFS(vertex, dfs_list);
			}
		}
		connectedComponents = count;
		return connectedComponents;
    }

	/**
	 * Perform a breadth-first search on the graph
	 * @return Array of vetices
	 */
    public void BFS(Vertex<E, T> root, DoublyLinkedList<Vertex<E, T>> bfs_list) {
		Queue<Vertex<E, T>> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()){
			root = queue.remove();
			root.setStatus(2); bfs_list.add(root);
			Vertex<E, T>[] neighbors = root.getNeighbors();
			for (Vertex<E, T> next : neighbors){
				if(next.getStatus() == 0) queue.add(next);
			}
		}
		
    }

	public Vertex<E, T>[] BFS() {
		DoublyLinkedList<Vertex<E, T>> bfs_list = new DoublyLinkedList<>();
		Vertex<E, T>[] verticesArray = vertices_array();

		for (Vertex<E, T> vertex : verticesArray){
			BFS(vertex, bfs_list);
		}
		// convert the list to an array
		Vertex<E, T>[] bfsArray = new Vertex[vertexList.size()];
		NodeIterator<Vertex<E, T>> iter = bfs_list.iterator();
		int index = 0;

		while(iter.hasNext()){
			Vertex<E, T> nextVertex = iter.next();
			bfsArray[index++] = nextVertex;
			nextVertex.setStatus(0);		// unvisit each node
		}

		return bfsArray;
	}

	/**
	 * Perform a depth-first search on the graph
	 * @return Array of vetices
	 */
    public void DFS(Vertex<E, T> vertex, DoublyLinkedList<Vertex<E, T>> dfs_list) {

		vertex.setStatus(2);
		dfs_list.add(vertex);

        Vertex<E, T>[] neighbours = vertex.getNeighbors();

		for (Vertex<E, T> next : neighbours){
			if (next.getStatus() == 0) DFS(next, dfs_list);		// if neighbor is not visited, perform DFS on neighbor
		}

    }	

	@SuppressWarnings("unchecked")
	public Vertex<E, T>[] DFS() {
		DoublyLinkedList<Vertex<E, T>> dfs_list = new DoublyLinkedList<>();
		Vertex<E, T>[] verticesArray = vertices_array();
        for (Vertex<E,T> vertex : verticesArray) {			// perform DFS on all vertices to ensure that all the connected components are covered
			if(vertex.getStatus() == 0) DFS(vertex, dfs_list);		// if vertex is not visited, perform DFS on vertex
		}

		@SuppressWarnings("unchecked")
		Vertex<E, T>[] dfsArray = new Vertex[vertexList.size()];
		NodeIterator<Vertex<E, T>> iter = dfs_list.iterator();
		int index = 0;
		while(iter.hasNext()){
			Vertex<E, T> nextVertex = iter.next();
			dfsArray[index++] = nextVertex;
			nextVertex.setStatus(0);		// unvisit each node
		}

		return dfsArray;
		
	}

}
