import java.util.List;

import java.util.ArrayList;

class MistFunction1{

    public static void main(String[] args) {
       System.out.println(mistFunction1(4,3));
    }
    
    public static int mistFunction1(int m, int n) {
        if (m == 1 && n == 1) return 1;
        if (m == 0 || n == 0) return 0;
        return mistFunction1(m-1, n) + mistFunction1(m, n-1);
    }
}

class MistFunction2{

    public static List<String> mesPieces;
    public static void main(String[] args) {
        mesPieces = new ArrayList<>();
        //add elements to mespieces
        mesPieces.add("Chambre");
        mesPieces.add("Salon");
        System.out.println(mistFunction2("SSalon1", mesPieces));
    }
    
    public static List<List<String>> mistFunction2(String target, List<String> pieces){
        List<List<String>>[] table = new ArrayList[target.length() + 1];

        for (int i=0 ; i <= target.length(); i++) {
            table[i] = new ArrayList<>();
        }

        table[0].add (new ArrayList<>());

        for (int i = 0; i <= target.length(); i++) {
            for (String piece: pieces) {

                if (i + piece.length() <= target.length() && target.startsWith(piece, i)) {
                    List<List<String>> newCombinations = new ArrayList<>();

                    for (List<String> subarray : table[i]) {
                        List<String> newSubarray = new ArrayList<> (subarray);
                        newSubarray.add(piece);
                        newCombinations.add(newSubarray);
                    }
                    table[i + piece.length()] .addAll(newCombinations);
                }
            }
            
        }
            return table[target.length()];
    }
}

class MistFunction3{
    static int[] mesOptions;
    public static void main(String[] args) {
        mesOptions = new int[3];
        //add elements to mespieces
        mesOptions[0]= 9;
        mesOptions[1]= 4;
        System.out.println(mistFunction3(4, mesOptions));
    }

    public static boolean mistFunction3(int target, int[] options) {
        boolean[] table = new boolean[target + 1];
        table[0] = true;

        for (int i = 0; i<= target; i++){
            if (table[i]) {
                for (int option:options) {
                    if (i+option <= target) {
                        table[i + option] = true;
                    }
                }
            }
        }
        return table[target];
    }
}