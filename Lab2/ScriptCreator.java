package Lab2;

public class ScriptCreator {
    public static void main(String[] args) {

        for (int j = 10; j <= 10000000; j = j * 10) {
            for (int i = 1; i < 10; i++) {
                for (int n = 0; n <= 4; n++) {
                    if (n < 3)
                        continue;
                    if (n == 2) {
                    System.out.println("java Lab2.java " + j * i + " " + n + " " + 5);
                    System.out.println("java Lab2.java " + j * i + " " + n + " " + 10);
                    System.out.println("java Lab2.java " + j * i + " " + n + " " + 20);
                    System.out.println("java Lab2.java " + j * i + " " + n + " " + 30);
                    System.out.println("java Lab2.java " + j * i + " " + n + " " + 50);
                    } else
                    System.out.println("java Lab2.java " + j * i + " " + n);

                }
            }
        }

    }
}
