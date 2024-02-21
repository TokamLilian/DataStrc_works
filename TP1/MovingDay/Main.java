package MovingDay;

import Stack.ArrayStack;

public class Main {
    private static int day = 10;

    public static void main(String[] args) {
        ArrayStack<String> Nam = new ArrayStack<>();
        for (int i=5; i>0; i--){
            String[] layers = {"Agriculture", "Manufacture", "Academia", "Government", "The King" };
            String layer = layers [i-1];
            Nam.Push("L"+ i +  ": " +layer );
        }

        ArrayStack<String> Mam = new ArrayStack<>();
        ArrayStack<String> Sam = new  ArrayStack<>();

        MovingDay.move(Nam, Mam, Sam, day);
    }   
}
