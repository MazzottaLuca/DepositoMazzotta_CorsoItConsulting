public class Array {
    public static void main(String[] args) {
        int[] numeri = new int[5];

        for (int i = 0; i < numeri.length; i++) {
            numeri[i] = i + 1; // visto che gli array partono da indice 0 la dimensione dell'array è l'ultimo
                               // indice + 1
        }
        for (int i = 0; i < numeri.length; i++) {
            System.out.println(numeri[i] + " ");
        }
        int[][] matrice = new int[3][3];
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                matrice[i][j] = i + j;
            }
        }
    }
}
