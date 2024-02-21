package CircularQueue;

public class GameSolver {
    private static int rows, columns = 3;
    int size = 6;

    public static void main(String[] args) {
        CircularQueue<Integer> queue = new CircularQueue<Integer>();
        start(queue);
    }

    public static void start(CircularQueue<Integer> queue){
        int [] tab = {0,1,2,1,2,0,2,1,1}; 
        
        for (int i = 0; i<tab.length; i++){
            queue.enqueue(tab[i]);
        }

        play(queue);
    }

    public static int play(CircularQueue<Integer> queue){

        int count = 0;                                            // the number of iterations taken to do all contaminations
        int maxSize = 10;
        int startIndex = 0;
        int queueIndex = queue.getFrontIndex();

        while (queue.checkInQueue(1)){                      // the game continues if there are still possible contaminations
            if (queue.get(queueIndex) == 2){                      // we proceed if the current element on the queue is a zombie

                int [] neighboors = getNeighboors(queue, startIndex);
                
                for (int neighboor : neighboors){
                    if (neighboor != -1){ 
                        if (queue.get(neighboor) == 1){ // problem with neighboor
                            queue.set(neighboor, 2);                    // all 1's are contaminated to zombies 
                        }
                    }
                }
                count++;
            }

            queueIndex = (queueIndex + 1) %maxSize;
            startIndex ++;
        }

        return count;
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
        return neighboors ;
    }
}
