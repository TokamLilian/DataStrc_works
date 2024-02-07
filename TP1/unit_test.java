import java.util.Scanner;

import Stack.ArrayStack;
import DoubleStack.ArrayDoubleStack;

public class unit_test {
    public static void main(String[] args){
        ArrayStack<String> Nam = new ArrayStack<>();
        pushNam(Nam);
        //System.out.println(Nam.toString());
        //System.out.println(Nam.Top());
    }

    public static void pushNam(ArrayStack<String> stack){
        // create new scanner
        Scanner input = new Scanner(System.in);
        
        for (int i=5; i>0; i--){
            //System.out.println("Enter a layer: ");
            //String layer = input.nextLine();
            String[] layers = {"Agriculture", "Manufacture", "Academia", "Government", "The King" };
            String layer = layers [i-1];
            stack.Push("L"+ i +  ": " +layer );
        }
        input.close();

    }
}