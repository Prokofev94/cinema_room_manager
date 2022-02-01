package cinema;

import java.util.*;

public class Cinema {
    static int ticketsSold = 0;
    static int totalIncome;
    static int currentIncome = 0;
    static String percentage = "0.00%";

    public static void main(String[] args) {
        char[][] room = getRoom();
        menu(room);
    }

    public static char[][] getRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        char[][] room = new char[rows][seats];
        for (char[] row : room) {
            Arrays.fill(row, 'S');
        }
        totalIncome = rows * seats > 60 ? rows / 2 * seats * 10 + (rows - rows / 2) * seats * 8 : rows * seats * 10;
        return room;
    }

    public static void menu(char[][] room) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                showSeats(room);
                menu(room);
                break;
            case 2:
                buyTicket(room);
                menu(room);
                break;
            case 3:
                statistic();
                menu(room);
                break;
            default:
                break;
        }
    }

    public static void showSeats(char[][] room) {
        System.out.print("\nCinema:\n ");
        for (int i = 1; i <= room[0].length; i++) {
            System.out.print(" " + i);
        }
        for (int i = 1; i <= room.length; i++) {
            System.out.print("\n" + i);
            for (int j = 0; j < room[0].length; j++) {
                System.out.print(" " + room[i - 1][j]);
            }
        }
        System.out.println("\n");
    }

    public static void buyTicket(char[][] room) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter a row number:");
        int r = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int s = scanner.nextInt();
        if (r < 1 || r > room.length || s < 1 || s > room[0].length) {
            System.out.println("Wrong input!");
            buyTicket(room);
        } else if (room[r - 1][s - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            buyTicket(room);
        } else {
            room[r - 1][s - 1] = 'B';
            int price = room.length * room[0].length > 60 && r > room.length / 2 ? 8 : 10;
            ticketsSold++;
            percentage = String.format("%.2f", 100.0 / room.length / room[0].length * ticketsSold) + "%";
            currentIncome += price;
            System.out.println("Ticket price: $" + price + "\n");
        }
    }

    public static void statistic() {
        System.out.printf("\nNumber of purchased tickets: %d\n", ticketsSold);
        System.out.printf("Percentage: %s\n", percentage);
        System.out.printf("Current income: $%d\n", currentIncome);
        System.out.printf("Total income: $%d\n\n", totalIncome);
    }
}