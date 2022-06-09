import java.net.FileNameMap;
import java.util.*;
import java.io.*;

public class ReceiptPopulator {

    static class Item {
        public boolean byWeight;
        public String displayName;
        public double unitPrice;
        public double quantity;

        public Item(boolean isCash, String displayName, double unitPrice, double quantity) {
            this.byWeight = isCash;
            this.displayName = displayName;
            this.unitPrice = unitPrice;
            this.quantity = quantity;
        }

    }

    private static Item readLine(String line) {
        Scanner sc = new Scanner(line);
        sc.useDelimiter(",");
        String displayName = sc.next();
        Double unitPrice = sc.nextDouble();
        boolean byWeight = sc.nextInt() == 1;
        sc.close();

        Item tempItem = new Item(byWeight, displayName, unitPrice, 0);

        return tempItem;
    }

    private static Hashtable<Integer, Item> readFromCSV(String fileName) throws FileNotFoundException {
        Hashtable<Integer, Item> inventory = new Hashtable<Integer, Item>();

        Scanner sc = new Scanner(new File(fileName));
        sc.useDelimiter("\n");
        int index = 1;
        while (sc.hasNext()) {
            inventory.put(index, readLine(sc.nextLine()));
            index++;
        }
        sc.close();

        return inventory;
    }

    public static void main(String[] args) throws FileNotFoundException{
        // import random

        // random.seed(0xDEADBEEF)

        // with open("employees.txt") as employee_file:
        // employees = [e.strip() for e in employee_file.readlines()]

        // # print(employees)

        // # temporary
        // last_receipt_id = 1
        // v
        // script = ""
        // items = list(range(1,54))
        // holidays = [6, 13]
        // month = 2
        // for day in range(1, 22):
        // transaction_count = random.randrange(5, 10)
        // if day in holidays:
        // transaction_count = random.randrange(50, 100)
        // for n in range(transaction_count):
        // this_receipt_id = last_receipt_id
        // last_receipt_id += 1
        // for l in range(random.randrange(1, 5)):
        //     script += "INSERT INTO receipt_lines VALUES ({}, {}, {:0.3f});\n".format(this_receipt_id, random.choice(items), random.random() * 25)
        // script += "INSERT INTO receipts (id, transaction_date, total, is_cash, employee_id) VALUES ({}, '2022-{:02d}-{:02d}', {:0.2f}, {}, 1);\n".format(this_receipt_id, month, day, random.random() * 100, random.choice(["true", "false"]))
        // print(script)
        long seed = 0xDEADBEEF;

        Random rnd = new Random();
        rnd.setSeed(seed);

        Hashtable<Integer, Item> inventory = readFromCSV("items.csv");

        

    }
}
