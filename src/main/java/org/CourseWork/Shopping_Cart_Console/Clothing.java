package org.CourseWork.Shopping_Cart_Console;

public class Clothing extends Product {

    private String size;
    private String color;


    //declare constructor for clothing
    public Clothing(String productId, String productName, int numberofItems, double price, String objecttype, String size, String color) {
        super(productId, productName, numberofItems, price, objecttype);
        this.size = size;
        this.color = color;
    }


    //getters for colors and size
    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }


    //Implement the productwritedetails method which override in Product class
    public String productwritedetails() {
        return (getProductId()+"  "+getProductName()+"  "+getNumberofavailableItems()+"  "+getPrice()+"  "+getSize()+"  "+getColor()+"  "+getObjecttype());
    }

    //Implement the productdisplaydetails method which override in Product class
    @Override
    public String productdisplaydetails() {
        return ("Product ID: "+getProductId()+"\n"+"Product Name: "+getProductName()+"\n"+"no of available items: "+getNumberofavailableItems()+"\n"+"Price: "+getPrice()+"\n"+"Size: "+getSize()+"\n"+"Color: "+getColor()+"\n"+"object type: "+getObjecttype());
    }
}
