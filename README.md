# Restaurant Management System - README

## Introduction
The **Restaurant Management System** is a Java-based application designed to manage restaurant orders, seating arrangements, and a customer waiting queue. It utilizes **Linked Lists, Arrays, and Queues** for efficient data handling. This system allows users to place orders, manage table availability, and maintain a waiting queue.

## Features
1. **Menu Management** - Displays available menu items with prices.
2. **Order Placement** - Customers can place online orders for a specific table.
3. **Table Management** - Monitors occupied and available tables.
4. **Waiting Queue** - Manages customers waiting for tables.
5. **Bill Calculation** - Computes the total bill for orders.
6. **Order Display** - Shows active orders with details.
7. **Table Freeing** - Frees tables when customers leave and assigns tables to waiting customers.

## File Structure
- **Linkedlist.java** - Implements a linked list to store orders.
- **Arraylist.java** - Implements a dynamic array for menu management.
- **Queue.java** - Implements a queue to manage waiting customers.
- **RestaurantManagementSystem.java** - The main class handling all functionalities.

## Classes and Their Roles
### 1. **Linkedlist**
   - Implements a singly linked list.
   - Stores active orders.

### 2. **Arraylist**
   - Implements a dynamic array.
   - Manages the menu items.

### 3. **Queue**
   - Manages the waiting list for customers.

### 4. **RestaurantManagementSystem**
   - Main class controlling the restaurant operations.
   - Handles customer orders, table allocation, and bill generation.

## How to Run the Project
### Prerequisites
- Java Development Kit (JDK) installed.
- A code editor (e.g., IntelliJ, Eclipse, or VS Code).

### Steps to Run
1. **Download or Clone the Repository**
2. **Compile the Java Files**
   ```sh
   javac RestaurantManagementSystem.java
   ```
3. **Run the Application**
   ```sh
   java RestaurantManagementSystem
   ```

## Usage
- **New Customer**: Enter a name and select a table number.
- **Free a Table**: Removes an order and displays the bill.
- **Display Orders**: Shows all current orders.
- **View Waiting Queue**: Displays customers in the queue.
- **Exit**: Closes the application.

## Future Enhancements
- Implement a **GUI** for better user interaction.
- Add a **database** to store persistent data.
- Introduce **online payment integration**.

## Conclusion
The **Restaurant Management System** simplifies restaurant operations, improving efficiency in order management and customer handling. Future improvements can make the system even more robust and user-friendly.

