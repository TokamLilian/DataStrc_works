package MovingDay;

import Stack.ArrayStack;

public class Main {
    public static void main(String[] args) {
        ArrayStack<String> nam = new ArrayStack<>();
        nam.Push("N1: Agriculture");
        nam.Push("N2: Manufacture");
        nam.Push("N3: Académie");
        nam.Push("N4: Gouvernement");
        nam.Push("N5: Le Roi");

        ArrayStack<String> pam = new ArrayStack<>();

        ArrayStack<String> sam = new ArrayStack<>();

        MovingDay.moveCity(nam, pam, sam, 10);
    }
}
