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

    static class Order {
        public int orderID;
        public double cost;
        public HashMap<Integer, Double> itemStock;
        public int year, month, day;

        private final String LINE_FMT = "INSERT INTO order_lines VALUES (%d, %d, %0.3f);";
        private final String ORDER_FMT = "INSERT INTO orders VALUES (%d, %0.2f, %04d-%02d-%02d, %s);";

        public Order(int orderID, double cost, int year, int month, int day) {
            this.orderID = orderID;
            this.cost = cost;
            this.itemStock = new HashMap<>();
            this.year = year;
            this.month = month;
            this.day = day;
        }

        public boolean hasOrdered(int ID) {
            return itemStock.containsKey(ID);
        }

        public void addItem(int ID, double newAmount, HashMap<Integer, Item> inventory) {
            if (hasOrdered(ID)) {
                return;
            }

            cost += newAmount * inventory.get(ID).unitPrice;

            itemStock.put(ID, newAmount);
        }

        public String toSQL() {
            String commands = "";
            for(Integer ID: itemStock.keySet()){
                String lineSQL = String.format(LINE_FMT, orderID, ID, itemStock.get(ID));
                commands += lineSQL + "\n";
            }

            commands+= String.format(ORDER_FMT, orderID, cost, year, month, day, "true");

            return commands;
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

    private static HashMap<Integer, Item> readFromCSV(String fileName) throws FileNotFoundException {
        HashMap<Integer, Item> inventory = new HashMap<Integer, Item>();

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

        HashMap<Integer, Item> inventory = readFromCSV("items.csv");



    }
}
