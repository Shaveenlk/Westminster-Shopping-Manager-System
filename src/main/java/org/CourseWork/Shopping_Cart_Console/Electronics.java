package org.CourseWork.Shopping_Cart_Console;

public class Electronics extends Product{

    private String brand;
    private int warrantyperiod;

    //implement constructor for Electronics
    public Electronics(String productId, String productName, int numberofItems, double price, String objecttype, String brand, int warrantyperiod) {
        super(productId, productName, numberofItems, price, objecttype);
        this.brand = brand;
        this.warrantyperiod = warrantyperiod;

    }

    //getters for brand and warrantyPeriod

    public String getBrand() {
        return brand;
    }

    @Override
    public int getWarrantyPeriod() {
        return warrantyperiod;
    }

    //Implement the productwritedetails method which override in Product class
    @Override
    public String productwritedetails() {
        return (getProductId()+"  "+getProductName()+"  "+getNumberofavailableItems()+"  "+getPrice()+"  "+getBrand()+"  "+getWarrantyPeriod()+"  "+getObjecttype());
    }

    //Implement the productdisplaydetails method which override in Product class
    @Override
    String productdisplaydetails() {
        return ("Product ID: "+getProductId()+"\n"+"Product Name: "+getProductName()+"\n"+"no of available items: "+getNumberofavailableItems()+"\n"+"Price: "+getPrice()+"\n"+"Brand: "+getBrand()+"\n"+"Warranty Period: "+getWarrantyPeriod()+"\n"+"object type: "+getObjecttype());
    }


}
