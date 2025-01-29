import java.util.*;

class Linkedlist {
    Node head;
    Node tail;
    int size = 0;

    class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
            this.next = null;
        }
    }

    public void add(Object data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void remove(Object data) {
        if (head == null) return;

        if (head.data.equals(data)) {
            head = head.next;
            if (head == null) tail = null;
            size--;
            return;
        }

        Node current = head;
        while (current.next != null && !current.next.data.equals(data)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            if (current.next == null) tail = current;
            size--;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
class Arraylist {
    public Object[] array;
    public int size;

    public Arraylist() {
        array = new Object[10]; // Initial capacity
        size = 0;
    }

    public void add(Object data) {
        if (size == array.length) {
            resize();
        }
        array[size++] = data;
    }

    public void remove(Object data) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(data)) {
                for (int j = i; j < size - 1; j++) {
                    array[j] = array[j + 1];
                }
                size--;
                break;
            }
        }
    }

    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return array[index];
    }

    public int size() {
        return size;
    }

    private void resize() {
        Object[] newArray = new Object[array.length * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " \n");
        }
        System.out.println();
    }
}

class Queue {
    Node front;
    Node rear;

    class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(String data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public void dequeue() {
        if (isEmpty()) {
            System.out.println("Underflow");
            return;
        }
        front = front.next;
        if (front == null) {
            rear = null;
        }
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        Node temp = front;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
}

class RestaurantManagementSystem {

    // Updated menu with prices (price for each item)
    Arraylist menu = new Arraylist();
    Linkedlist orders = new Linkedlist();
    Queue waitingQueue = new Queue();

    class MenuItem {
        String name;
        double price;

        MenuItem(String name, double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public String toString() {
            return name + " - Rs " + price ;
        }
    }

    class Order {
        int tableNumber;
        Arraylist items;

        Order(int tableNumber, Arraylist items) {
            this.tableNumber = tableNumber;
            this.items = items;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Table " + tableNumber + ": ");
            for (int i = 0; i < items.size(); i++) {
                sb.append(items.get(i)).append(", ");
            }
            return sb.toString();
        }

        // Calculate the total bill for the order
        public double calculateBill() {
            double total = 0;
            for (int i = 0; i < items.size(); i++) {
                MenuItem item = (MenuItem) items.get(i);
                total += item.price;
            }
            return total;
        }
    }

    public RestaurantManagementSystem() {
        // Add menu items with prices
        menu.add(new MenuItem("Salad", 300));
        menu.add(new MenuItem("Burger", 800));
        menu.add(new MenuItem("Pizza", 1200));
        menu.add(new MenuItem("Pasta", 700));
    }

    public void displayMenu() {
        System.out.println("\nMenu:");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i));
        }
    }


    public void placeOnlineOrder(int tableNumber, String customerName) {
        Scanner scanner = new Scanner(System.in);
    
        // Validate the table number
        while (tableNumber < 1 || tableNumber > 10) {
            System.out.println("Invalid table number. Please enter a number between 1 and 10: ");
            tableNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        }
    
        if (isTableOccupied(tableNumber)) {
            System.out.println("Table " + tableNumber + " is already occupied.");
    
            Arraylist freeTables = getFreeTables();
            if (freeTables.size() > 0) {
                System.out.println("Available tables: ");
                freeTables.display();
                System.out.print("Choose a free table or type 'wait' to join the waiting queue: ");
    
                String input = scanner.nextLine();
    
                if (input.equalsIgnoreCase("wait")) {
                    addToWaitingQueue(customerName);
                    return;
                }
    
                try {
                    tableNumber = Integer.parseInt(input);
                    if (tableNumber < 1 || tableNumber > 10) {
                        System.out.println("Invalid table number. Adding you to the waiting queue.");
                        addToWaitingQueue(customerName);
                    } else if (!isTableOccupied(tableNumber)) {
                        placeOrder(tableNumber, customerName);
                    } else {
                        System.out.println("Table " + tableNumber + " is not available. Adding you to the queue.");
                        addToWaitingQueue(customerName);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Adding you to the queue.");
                    addToWaitingQueue(customerName);
                }
            } else {
                System.out.println("No free tables available. Adding you to the waiting queue.");
                addToWaitingQueue(customerName);
            }
        } else {
            placeOrder(tableNumber, customerName);
        }
    }
    public void placeOrder(int tableNumber, String customerName) {
        Scanner scanner = new Scanner(System.in);
        Arraylist items = new Arraylist();

        System.out.println("\nHello, " + customerName + ". Here is the menu:");
        displayMenu();
        System.out.println("Enter the items to order (type 'done' to finish):");

        while (true) {
            String item = scanner.nextLine();
            if (item.equalsIgnoreCase("done")) {
                break;
            }

            boolean itemExists = false;
            for (int i = 0; i < menu.size(); i++) {
                MenuItem menuItem = (MenuItem) menu.get(i);
                if (menuItem.name.equalsIgnoreCase(item)) {
                    itemExists = true;
                    items.add(menuItem); // Add the menu item with the correct price
                    break;
                }
            }

            if (!itemExists) {
                System.out.println("Item not available on the menu: " + item);
            }
        }

        if (items.size() > 0) {
            orders.add(new Order(tableNumber, items));
            System.out.println("Order placed for table " + tableNumber + " by " + customerName + ": ");
            items.display();
        } else {
            System.out.println("No valid items were ordered. Order not placed.");
        }
    }
    
    private boolean isTableOccupied(int tableNumber) {
        Linkedlist.Node current = orders.head;
        while (current != null) {
            Order order = (Order) current.data;
            if (order.tableNumber == tableNumber) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    private Arraylist getFreeTables() {
        final int TOTAL_TABLES = 10;
        Arraylist freeTables = new Arraylist();

        for (int i = 1; i <= TOTAL_TABLES; i++) {
            if (!isTableOccupied(i)) {
                freeTables.add(i);
            }
        }

        return freeTables;
    }
    public void displayOrders() {
        System.out.println("\nCurrent Orders:");
        orders.display();
    }

    public void addToWaitingQueue(String customerName) {
        waitingQueue.enqueue(customerName);
        System.out.println(customerName + " added to the waiting queue.");
    }

    public void viewWaitingQueue() {
        System.out.println("\nWaiting Queue:");
        if (waitingQueue.isEmpty()) {
            System.out.println("No customers in the waiting queue.");
        } else {
            waitingQueue.display();
        }
    }

    public void serveNextCustomer() {
        if (waitingQueue.isEmpty()) {
            System.out.println("No customers in the waiting queue.");
        } else {
            String customerName = waitingQueue.front.data;
            waitingQueue.dequeue();
            System.out.println("Serving customer: " + customerName);
        }
    }

    public void freeTable(int tableNumber) {
        if (tableNumber < 1 || tableNumber > 10) {
            System.out.println("Invalid table number. Please enter a number between 1 and 10.");
            return;
        }
        Linkedlist.Node current = orders.head;
        while (current != null) {
            Order order = (Order) current.data;
            if (order.tableNumber == tableNumber) {
                orders.remove(order);
                System.out.println("Table " + tableNumber + " is now free.");

                // Calculate and display the bill for the customer
                double totalBill = order.calculateBill();
                System.out.println("Bill for table " + tableNumber + ": Rs " + totalBill);

                if (!waitingQueue.isEmpty()) {
                    String customerName = waitingQueue.front.data;
                    waitingQueue.dequeue();
                    System.out.println("Allocating table " + tableNumber + " to customer: " + customerName);
                    placeOrder(tableNumber, customerName);
                }
                return;
            }
            current = current.next;
        }
        System.out.println("Table " + tableNumber + " is already free.");
    }
    public static void main(String[] args) {
        RestaurantManagementSystem rms = new RestaurantManagementSystem();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Restaurant Management System ---");
            System.out.println("1. New Customer");
            System.out.println("2. Free a Table");
            System.out.println("3. Display Orders");
            System.out.println("4. View Waiting Queue");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String customerName = scanner.nextLine();

                    System.out.print("Enter table number for online order: ");
                    int tableNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    rms.placeOnlineOrder(tableNumber, customerName);
                    break;

                case 2:
                    System.out.print("Enter table number to free: ");
                    tableNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    rms.freeTable(tableNumber);
                    break;

                case 3:
                    rms.displayOrders();
                    break;

                case 4:
                    rms.viewWaitingQueue();
                    break;

                case 5:
                    System.out.println("Exiting the system.");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}