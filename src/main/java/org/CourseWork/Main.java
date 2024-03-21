package org.CourseWork;


import org.CourseWork.Shopping_Cart_Console.User;
import org.CourseWork.Shopping_Cart_Console.WestminsterShoppingManager;
import org.CourseWork.Shopping_Cart_GUI.ShoppingGUI;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        // Load the products details which saved fle from the westminsterShopping Manager
        WestminsterShoppingManager.loadFromFile();
        while (true){
            Scanner input = new Scanner(System.in);
            try {
                System.out.println("Welcome to Westminster Shopping Manger");
                System.out.println();
                System.out.println("1 for User Login");
                System.out.println("2 for Manager Login");
                System.out.println("0 for Exit the programme");

                int option = input.nextInt();
                switch (option) {
                    case 0:
                        System.exit(0);
                    case 1:
                        userAuthenticate();
                        break;
                    case 2:
                        WestminsterShoppingManager westminsterShoppingManager = new WestminsterShoppingManager();
                        westminsterShoppingManager.Menu();
                        break;
                    default:
                        System.out.println("Enter the valid input");
                }
            }catch (Exception e){
                System.out.println("Enter the valid input");
                input.nextLine();;
            }
        }
    }

    //checks the user information
    private static void userAuthenticate() {
        User user =new User();
        Scanner input =new Scanner(System.in);
        System.out.println("Enter the Username");
        user.setUsername(input.next());
        System.out.println("Enter the password");
        user.setPassword(input.next());

        user.storeDatabase();
        new ShoppingGUI();

    }
}