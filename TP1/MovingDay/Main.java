package strucdonnees;

public class Main {
    public static void main(String[] args) {
        ArrayStack<String> nam = new ArrayStack<>();
        nam.push("N1: Agriculture");
        nam.push("N2: Manufacture");
        nam.push("N3: Académie");
        nam.push("N4: Gouvernement");
        nam.push("N5: Le Roi");

        ArrayStack<String> pam = new ArrayStack<>();

        ArrayStack<String> sam = new ArrayStack<>();

        MovingDay.moveCity(nam, pam, sam, 10);
    }
}
