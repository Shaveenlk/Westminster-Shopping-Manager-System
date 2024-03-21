package org.CourseWork.Shopping_Cart_Console;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


//taking methods from shopping Manager Interface
public class WestminsterShoppingManager implements ShoppingManager {

    //Products added by the manager gets stored on arraylist
    public static ShoppingCart ManagerCart = new ShoppingCart();

    //To call the methods which define in Shopping Manger Interface
    public static WestminsterShoppingManager westminsterShoppingManger = new WestminsterShoppingManager();


    //implement menu method to get the manager inputs
    public void Menu() {
        Scanner input = new Scanner(System.in);
        int num;
        while (true) {
            try {
                System.out.println();
                System.out.println("1. Add a new product");
                System.out.println("2. Delete a product");
                System.out.println("3. Print the list of products");
                System.out.println("4. Save to file");
                System.out.println("0. Exit");
                System.out.println("Enter your choice: ");

                num = input.nextInt();

                switch (num) {
                    case 0:
                        System.exit(0);
                    case 1:
                        westminsterShoppingManger.addProduct();
                        break;
                    case 2:
                        westminsterShoppingManger.removeProduct();
                        break;
                    case 3:
                        westminsterShoppingManger.displayProducts();
                        break;
                    case 4:
                        westminsterShoppingManger.saveFile();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Enter the valid option");
                input.nextLine();  // Consume the newline character
            }
        }
    }


    //override the addProduct method which is created on Shopping Manager Interface
    @Override
    public void addProduct() {
        if (ManagerCart.getProductList().size() <= 50) {
            Scanner input = new Scanner(System.in);
            System.out.println("Choose the product type");
            System.out.println("1 for Electronics");
            System.out.println("2 for Clothing :");
            int producttype;

            //validates the product type
            while(true){
                producttype = input.nextInt();
                if (producttype ==1 || producttype==2){
                    break;
                }else{
                    System.out.println("Enter valid product type");
                    input.nextLine();
                }
            }

            System.out.println("Product ID");
            String productid = input.next();
            System.out.println("Product Name");
            String productname = input.next();
            System.out.println("no of items");
            int productitems = input.nextInt();
            System.out.println("Price");
            double price = input.nextDouble();

            //get ealative product information
            if (producttype == 1) {
                System.out.println("Enter the brand");
                String brand = input.next();
                System.out.println("Enter the warranty period in months");
                int warrantyperiod = input.nextInt();

                //adding details to the electronic constructor
                Electronics electronics = new Electronics(productid, productname, productitems, price, "electronics", brand, warrantyperiod);

                //adding electronic items to the shopping cart
                ManagerCart.addProduct(electronics);
                System.out.println("Electronic added successfully");
            } else if (producttype == 2) {
                System.out.println("Enter the Size");
                String size = input.next();
                System.out.println("Enter the color");
                String color = input.next();

                //adding details to the clothing constructor
                Clothing clothing = new Clothing(productid, productname, productitems, price, "clothing", size, color);

                //adding clothing items to the shopping cart
                ManagerCart.addProduct(clothing);
                System.out.println("Clothing added successfully");
            } else {
                System.out.println("product not found");
            }
        } else {
            System.out.println("you have entered the maximum limit");
        }
    }



    //override the removeProduct method which is created on Shopping Manager Interface
    @Override
    public void removeProduct() {

        //diplays the products which manager entered to the cart
        displayProducts();

        Scanner input = new Scanner(System.in);

        System.out.println("Chose the product type");
        System.out.println("1 for Electronics");
        System.out.println("2 for Clothing :");
        int producttype;

        //validates the product type
        while(true){
            producttype = input.nextInt();
            if (producttype ==1 || producttype==2){
                break;
            }else{
                System.out.println("Enter valid product type");
                input.nextLine();
            }
        }

        System.out.println("Enter product ID");
        String productId = input.next();

        // used when returning the delete items to confirm
        boolean itemdeleted = false;

        switch (producttype) {
            case 1:
                for (int i = 0; i < ManagerCart.getProductList().size(); i++) {
                    if (productId.equals(ManagerCart.getProductList().get(i).getProductId()) && "electronics".equals(ManagerCart.getProductList().get(i).getObjecttype())) {
                        System.out.println(ManagerCart.getProductList().get(i).productdisplaydetails());

                        //deleting electronics product
                        ManagerCart.deleteProduct(ManagerCart.getProductList().get(i));
                        itemdeleted = true;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < ManagerCart.getProductList().size(); i++) {
                    if (productId.equals(ManagerCart.getProductList().get(i).getProductId()) && "clothing".equals(ManagerCart.getProductList().get(i).getObjecttype())) {
                        System.out.println(ManagerCart.getProductList().get(i).productdisplaydetails());

                        //deleting clothing product
                        ManagerCart.deleteProduct(ManagerCart.getProductList().get(i));
                        itemdeleted = true;
                    }
                }
                break;
            default:
                System.out.println("Invalid product type");
        }
        if (itemdeleted) {
            System.out.println();
            System.out.println("Product deleted successfully");
            System.out.println();
            System.out.println("Items reaming in the cart: " + ManagerCart.getProductList().size()); //display remaining the cart
        } else {
            System.out.println("Product Not Found");
        }
    }



    //override the displayProducts method which is created on Shopping Manager Interface
    @Override
    public void displayProducts() {
        if (ManagerCart.getProductList().isEmpty()) {
            System.out.println("Product cart is Empty");
        } else {
            for (int i = 0; i < ManagerCart.getProductList().size(); i++) {
                System.out.println(ManagerCart.getProductList().get(i).productdisplaydetails());
                System.out.println();
            }
        }
    }

    // Override the saveFile method which is created on Shopping Manager Interface
    @Override
    public void saveFile() {
        try {
            // Create a FileWriter object to write to the file "savedProducts.txt".
            FileWriter myWriter = new FileWriter("savedProducts.txt");
            for (int i = 0; i < ManagerCart.getProductList().size(); i++) {
                //writing details to the file line by line
                myWriter.write(ManagerCart.getProductList().get(i).productwritedetails() + "\n");
            }
            // Close the FileWriter
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    // Override the loadFromFile method which is created on Shopping Manager
    public static void loadFromFile() {
        try {
            //creating a file object
            File myObj = new File("savedProducts.txt");

            //create a scanner object to read the file
            Scanner myReader = new Scanner(myObj);

            //load the details line by line
            while (myReader.hasNextLine()) {
                String dataLine = myReader.nextLine();
                // Split the line into an array  based on whitespace
                String[] data = dataLine.split("\\s+");

                if (data.length == 7) {
                    if (data[6].equals("electronics")) {
                        Electronics electronics = new Electronics(data[0], data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3]), data[6],data[4], Integer.parseInt(data[5]));
                        ManagerCart.addProduct(electronics);
                    } else if (data[6].equals("clothing")) {
                        Clothing clothing = new Clothing(data[0], data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3]),data[6],data[4], data[5]);
                        ManagerCart.addProduct(clothing);
                    }
                } else {
                    System.err.println("Invalid data format in the file.");
                }
            }

            //close the scanner
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}




