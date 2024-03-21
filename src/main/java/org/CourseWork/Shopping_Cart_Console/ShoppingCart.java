package org.CourseWork.Shopping_Cart_Console;

import java.util.ArrayList;
import java.util.Collections;


public class ShoppingCart{

    //used to store products which manger and user puts
    private   ArrayList<Product> productList;
    public double discount_value,discounted_cost;


    //constructor for shoopingcart
    public ShoppingCart() {
        this.productList =new ArrayList<>();
    }

    //method to addproduct to the shopping cart
    public  void addProduct(Product product) {
        productList.add(product);
    }


    //method to get the list of products in the shopping cart.
    public ArrayList<Product> getProductList() {
        Collections.sort(productList); // Sorting the productList
        return productList;
    }


    //method for deleteproducts in the shopping cart
    public void deleteProduct(Product product){
        productList.remove(product);
    }

    //method to calculate the totalcost of shoppingcart
    public double totalCost(){
        double totalcost =0.0;
        for (int i = 0; i < productList.size(); i++) {
            totalcost =totalcost+(productList.get(i).getPrice()*productList.get(i).getNumberofavailableItems());
        }
        return  totalcost;
    }

    //check the user information if the user is the new user.implement the new user discount
    public double firstDiscount(Boolean newAccount){
        discount_value =0;
        if(newAccount){
            discount_value =totalCost()*0.1;
        }
        return discount_value;
    }

    //calculate three or more items which in the same category
    public double category_Discount(){
        int electronic_count = 0;
        int clothing_count = 0;
        double promotion = 0;
        discounted_cost = 0;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getObjecttype().equals("electronics")){
                electronic_count++;
            }else if (productList.get(i).getObjecttype().equals("clothing")){
                clothing_count++;
            }
        }
        if (electronic_count >= 3 || clothing_count >= 3){
            promotion = 0.2;

        }

        if (promotion != 0) {
            discounted_cost = totalCost() * promotion;
            return discounted_cost;
        }else {
            return discounted_cost;
        }
    }

    //Implement the finaltotal method
    public double finaltotal(){
        double final_total = totalCost()-discounted_cost- discount_value;
        return final_total;
    }
}
