package MovingDay;

import Stack.ArrayStack;

public class MovingDay {

    public static void printProgressCity(String cityName, ArrayStack<String> city) {
        if (!city.isEmpty()) System.out.println(cityName + "(" + city.Top() + ")");
        else System.out.println(cityName + "(None)");
    }

    /**
     * Moving sections from "Nam" city to "Sam" city using and intermediary city "Mam"
     * @param Nam
     * @param Mam
     * @param Sam
     * @param day
     */
    public static void move(ArrayStack<String> Nam, ArrayStack<String> Mam, ArrayStack<String> Sam, int day){
        int originalSize = Nam.size();
        for (int  i = 0; i < day; i++) {
            if (!Nam.isEmpty()) {
                Mam.Push(Nam.Pop());

            }else if (!Mam.isEmpty()) {
                Sam.Push(Mam.Pop());
            }

            System.out.println("Day " + i + ":");
            printProgressCity("Nam", Nam);
            printProgressCity("Mam", Mam);
            printProgressCity("Sam", Sam);
        }

        String msg;
        if (!Nam.isEmpty() && Sam.size() == originalSize){
            msg = "<POSSIBLE>";
        } else{
            msg = "<NOT POSSIBLE>";
            int remaingDays = day + Mam.size();
            System.out.println(remaingDays + " days are required, hence");
            
        } System.out.println(msg);

    }

}
