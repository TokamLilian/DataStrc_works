package MovingDay;

import Stack.ArrayStack;

public class MovingDay {
    private static int day = 0;

    public static void printProgressCity(String city, String layer) {
        System.out.println(city + "(" + layer + ")");
    }

    public static void move(ArrayStack<String> Nam){
        ArrayStack<String> Mam = new ArrayStack<>();
        ArrayStack<String> Sam = new  ArrayStack<>();

        System.out.println("Day " + day + ":");
        printProgressCity("Nam", Nam.Pop());
        printProgressCity("Sam", Sam.Pop());
        printProgressCity("Mam", Mam.Pop());

    }

}
