package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        CategoryService service = new CategoryService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== CATEGORY MENU ===");
            System.out.println("1. Add category");
            System.out.println("2. Show all");
            System.out.println("3. Update category");
            System.out.println("4. Delete category");
            System.out.println("0. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1 -> {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    service.addCategory(name);
                }

                case 2 -> service.getAll();

                case 3 -> {
                    System.out.print("Enter id: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new name: ");
                    String name = sc.nextLine();
                    service.update(id, name);
                }

                case 4 -> {
                    System.out.print("Enter id: ");
                    int id = sc.nextInt();
                    service.delete(id);
                }

                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }

                default -> System.out.println("Invalid option");
            }
        }
    }
}