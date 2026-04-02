public class MetodiArray {
    public static void main(String[] args) {
        int[]numeri = {1, 2, 3};
        modifica(numeri);
        System.out.println(numeri[0]);
    }
static void modifica(int[] arr) {
    arr[0] = 99;
}
}