package Lab2;

import java.util.Random;

public class GenerateArray {
    public static void main(String[] args) {
        int nrElements = Integer.parseInt(args[0]);
        System.out.print(Integer.toString(nrElements) + " ");
        Random r = new Random(4337);
        for (int i = 0; i < nrElements; i++) {
            int x = r.nextInt();
            System.out.print(x + " ");
        }
    }
}
