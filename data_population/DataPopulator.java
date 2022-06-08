import java.io.*;
import java.util.Scanner;

public class DataPopulator {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(args[1]));
        sc.useDelimiter(",");   //sets￿the￿delimiter pattern
        while (sc.hasNext())  //returns a boolean Value
        {
            System.out.print(sc.next()); //find￿and￿returns￿the￿next￿complete￿token￿from￿this￿scanner￿￿
        }
        sc.close();//closes￿the￿scanner￿￿
    }
}