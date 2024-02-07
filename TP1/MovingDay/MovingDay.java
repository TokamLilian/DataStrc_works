package MovingDay;

import Stack.ArrayStack;

public class MovingDay {

    public static void moveCity(ArrayStack<String> nam, ArrayStack<String> pam, ArrayStack<String> sam, int days) {
        for (int day = 0; day < days; day++) {
            System.out.println("Jour " + day + ": Nam " + nam + ", Pam " + pam + ", Sam " + sam);
            

            // Déplacer une section de Nam à Sam
            if (!nam.isEmpty()) {
                sam.Push(nam.Pop());
            } else {
                // Si Nam est vide, déplacez une section de Pam à Sam
                if (!pam.isEmpty()) {
                    sam.Push(pam.Pop());
                }
            }
        }

            // Vérifier si l'ordre initial est rétabli à Sam
            if (sam.size() <= 5) {
                System.out.println("Il est POSSIBLE de déplacer la ville dans les 10 jours.");
                return;
            }
        

            else {
            	System.out.println("Il est Non possible de déplacer la ville dans les 10 jours. Car il faut " + sam.size() + " jours" );
            }
        }
}

