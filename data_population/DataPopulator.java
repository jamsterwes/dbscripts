import java.io.*;
import java.util.Scanner;

public class DataPopulator {
    public static void main(String[] args) throws FileNotFoundException {
        // Script text
        String script = "";

        // Scan items CSV
        Scanner sc = new Scanner(new File(args[0]));
        sc.useDelimiter(",");
        sc.nextLine();
        while (sc.hasNext()) {
            String displayName = sc.next();
            Double unitPrice = sc.nextDouble();
            boolean byWeight = sc.nextInt() == 1;
            if (sc.hasNext()) sc.nextLine();
            script += "INSERT INTO items (display_name, unit_price, by_weight) VALUES (";
            script += "'" + displayName + "', ";
            script += unitPrice.toString() + ", ";
            script += (byWeight ? "true" : "false") + ");\n";
        }
        sc.close();

        // Print out script
        System.out.print(script);
    }
}