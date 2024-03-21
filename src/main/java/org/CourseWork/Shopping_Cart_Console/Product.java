package org.CourseWork.Shopping_Cart_Console;

public abstract   class Product implements Comparable<Product> {
    private String objecttype;
    private String ProductId;
    private String ProductName;
    private int numberofavailableItems;
    private double price;

    //declare constructor for the Product
    public Product(String productId, String productName, int numberofavailableItems, double price,String objecttype) {
        this.ProductId = productId;
        this.ProductName = productName;
        this.numberofavailableItems = numberofavailableItems;
        this.price = price;
        this.objecttype =objecttype;
    }

    public void setNumberofavailableItems(int numberofavailableItems) {
        this.numberofavailableItems = numberofavailableItems;
    }

    public String getProductId() {
        return ProductId;
    }

    public String getProductName() {
        return ProductName;
    }

    public int getNumberofavailableItems() {
        return numberofavailableItems;
    }



    public double getPrice() {
        return price;
    }

    public String getObjecttype() {
        return objecttype;
    }


    public String getBrand(){
        return getBrand();
    }


    public int getWarrantyPeriod(){
        return  getWarrantyPeriod();
    }


    public String getSize(){
        return getSize();
    }

    public String getColor(){
        return getColor();
    }

    //sorting arrays through the product Id
    public int compareTo(Product product){
        return ProductId.compareTo(product.ProductId);
    }



    //implement productwritedetails methods which overrides from realative subclass
    abstract String productwritedetails();

    //implement productdisplaydetails methods which overrides from realative subclass
    abstract String productdisplaydetails();

}
