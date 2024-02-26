package tp2;

public class Main {
	 public static void main(String[] args) {
	        LinkedList myList = new LinkedList();
	        myList.addLast(10);
	        myList.addLast(20);
	        myList.addLast(30);
	        myList.addLast(-3);
	        myList.addLast(500);

	        System.out.println("Liste avant suppression de la valeur 20 : ");
	        myList.printList();

	        myList.removeValue(20);

	        System.out.println("Liste après suppression de la valeur 20 : ");
	        myList.printList();

	        System.out.println("Le 2ème élément en partant de la fin est : " + myList.returnNLast(2));
	        System.out.println("Est-ce que 15 est présent dans la liste ? " + myList.checkInList(15));
	        System.out.println("La valeur maximale dans la liste est : " + myList.maxValue());
	    
	 
			 CircularQueue circularQueue = new CircularQueue();
		
		     System.out.println("Enqueue: 1, 2, 3, 4");
		     circularQueue.enqueue(1);
		     circularQueue.enqueue(2);
		     circularQueue.enqueue(3);
		     circularQueue.enqueue(4);
		     circularQueue.print();
		
		     System.out.println("Size: " + circularQueue.size());
		     System.out.println("Front: " + circularQueue.front());
		     System.out.println("Rear: " + circularQueue.rear());
		
		     System.out.println("Check if 3 is in the queue: " + circularQueue.checkInQueue(3));
		
		     System.out.println("Remove 2 from the queue");
		     circularQueue.remove(2);
		     circularQueue.print();
		
		     System.out.println("Reverse the queue");
		     circularQueue.reverse();
		     circularQueue.print();
	 }
}
		 

