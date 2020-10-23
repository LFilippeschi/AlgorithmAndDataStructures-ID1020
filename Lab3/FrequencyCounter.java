package Lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * takes as input min length of string and size of the array
 */

public class FrequencyCounter {

    public int hash(String s, int M) {
        return (s.hashCode() & 0x7fffffff) % M;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(
                "/media/leonardo/SharedVolume/KTH1/Algorithms and data structures/Algorithm and data structure/Lab3/thetext.txt");
        Scanner fs = new Scanner(file);
        int count = 0;
        while (count++ < 20) {
            System.out.println(fs.next().hashCode());
        }
        fs.close();
    }
}
