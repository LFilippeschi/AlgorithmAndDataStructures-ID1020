package Lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SizeFile {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("Lab3/thetext.txt");
        Scanner sc = new Scanner(file);
        int count =0;
        while(sc.hasNext()){
            sc.next();
            count++;            
        }
        System.out.println(count);
        sc.close();
    }

    
}
