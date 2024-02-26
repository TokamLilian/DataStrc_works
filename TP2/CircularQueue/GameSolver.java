package CircularQueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameSolver {
    private static int rows, columns = 3;
    int size = 6;

    public static void main(String[] args) {
        CircularQueue<Integer> queue = new CircularQueue<Integer>();
        loadTextFile(queue);
        int count = play(queue);
        if(count == -1){
            queue.print();
            System.out.println("Remaining uncontaminated");
        }else{
            queue.print();
            System.out.println("Everyone contaminated in " + count + " turns.");
        }
    }

    private static void loadTextFile(CircularQueue<Integer> queue){
        Scanner scan;
        //String filePath = "sample.txt"; // for submission
        String filePath = "TP2\\CircularQueue\\sample.txt";  //what is used in my IDE
        int lineNumber = 0;
        try {
            scan = new Scanner(new File(filePath));
            while (scan.hasNext()) { 
                String line = scan.nextLine();
                
                try {
                    if (lineNumber > rows) break;
                    if (lineNumber == 0) {
                        String [] dimensions = line.split(" ");
                        rows = Integer.valueOf(dimensions[0]);
                        columns = Integer.valueOf(dimensions[1]);
                    }
                    else if (line.length()-line.length()/2 == columns ){
                        String [] entries = line.split(" ");
                        for (String entry : entries){
                            queue.enqueue(Integer.valueOf(entry));
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                lineNumber++;
            }

            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int play(CircularQueue<Integer> queue){

        int count = 0;                                            // the number of iterations taken to do all contaminations
        int maxSize = queue.getMaxSize();
        int startIndex = 0;                                       // Index of element on the grid (inorder to get the neighboors)
        int queueIndex = queue.getFrontIndex();
        boolean countedForCurrentLine = false;
        int currentLine = 0;

        while (queue.checkInQueue(1)){                      // the game continues if there are still possible contaminations
            if (isDone(queue)) return -1;
            
            try {
                
                if (queue.get(queueIndex) == 2){                      // we proceed if the current element on the queue is a zombie
                    
                    int [] neighboors = getNeighboors(queue, startIndex);
                    
                    for (int neighboor : neighboors){
                        if (neighboor != -1){ 
                            int queueNeighboor = (neighboor + maxSize/2) %maxSize;
                            if (queue.get(queueNeighboor) == 1){
                                queue.set(queueNeighboor, 2);                    // all 1's are contaminated to zombies 
                                //if(!countedForCurrentLine){ count++;System.out.println(count);queue.print();}
                                if(!countedForCurrentLine)count++;
                                countedForCurrentLine = true;
                            }
                        }
                    }
                } 
                
            }catch (IndexOutOfBoundsException e) {
                queueIndex = 0;                                        //restart to check the list again
            }

            queueIndex = (queueIndex + 1) %maxSize;
            startIndex ++;
            if (startIndex /columns != currentLine){countedForCurrentLine = false;}           //moving to the next line makes it possible to count number of contamination iterations
            currentLine = startIndex / columns;
            
        }

        return count;
    }

    /**
     * Returns false if there are still possible contaminations
     * and true otherwise, that is 
     * every present '1' is immunitized (surrounded by '0's)
     * @return
     */
    public static boolean isDone(CircularQueue<Integer> queue){
        int maxSize = queue.getMaxSize();
        int startIndex = 0;     
        int queueIndex = queue.getFrontIndex();

        //while (queue.checkInQueue(1)){
        while (startIndex < rows*columns){
            try {   
                if (queue.get(queueIndex) == 1){
                    
                    int [] neighboors = getNeighboors(queue, startIndex);
                    for (int neighboor : neighboors){
                        if (neighboor != -1){ 
                            int queueNeighboor = (neighboor + maxSize/2) %maxSize;
                            int neighboorElement = queue.get(queueNeighboor);
                            if (neighboorElement == 1 || neighboorElement == 2 ){
                                return false;                   // we return false as there is atleast one '1' not surrounded by '1' or '2's
                            }
                        }
                    }
                
            
                }
            } catch (IndexOutOfBoundsException e) {
                return true;  
            }
            startIndex ++;
            queueIndex = (queueIndex + 1) %maxSize;
        }
        return true;
    }

    /**
     * Returns an array containing the neighboors of an index on the grid
     * @param queue
     * @param index
     * @return
     */
    private static int[] getNeighboors(CircularQueue<Integer> queue, int index){
        int left, right, up, down;
        
        if (index % columns == 0) left = -1;  else left = index - 1;
        if ((index + 1) % columns == 0) right = -1; else right = index + 1;
        if (index < columns) up = -1; else up = index - columns;
        if (index > columns*(columns - 1)) down = -1;  else down = index + columns;

        int [] neighboors = {left, right, up, down};
        return neighboors;
    }
}
