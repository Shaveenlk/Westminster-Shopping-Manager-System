package org.CourseWork.Shopping_Cart_GUI;


import org.CourseWork.Shopping_Cart_Console.Product;
import org.CourseWork.Shopping_Cart_Console.ShoppingCart;
import org.CourseWork.Shopping_Cart_Console.WestminsterShoppingManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;




public class ShoppingGUI {

    //Shopping cart which user use
    public static ShoppingCart usercart = new ShoppingCart();
    public  static Product selectedProduct;

    private static JPanel displayPanel;
    private static JLabel productIDLabel, categoryLabel, nameLabel, noItemsLabel, miscLabel1, miscLabel2, displayLabel1;

    private static JButton addtocartbtn, shoppingCartButton;
    private static JTable table;

    private static String selectedValue;


    public ShoppingGUI() {

        //
        JFrame mainFrame = new JFrame("Westminster Shopping Center");
        mainFrame.setSize(800, 900);

        mainFrame.setLayout(new GridLayout(2, 1));


        JPanel toppanel = new JPanel(new GridLayout(2, 1));  //which include selected product category label,dropdown,shoppingcart button and the table
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2)); //which include selected product details and addtocart button
        JPanel panel2 = new JPanel(new GridLayout(1, 3)); //which include selected product category label,dropdown,shoppingcart

        JPanel panel3 = new JPanel(new GridBagLayout()); //inlude selected product category // Centered GridBagLayout
        JPanel panel4 = new JPanel(new GridBagLayout());  //inlude dropdown // Centered GridBagLayout
        JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //include shopping cart button // Centered GridBagLayout


        JPanel addToCartPanel = new JPanel(); //add to cart button
        addToCartPanel.setLayout(new BoxLayout(addToCartPanel, BoxLayout.Y_AXIS));


        displayPanel = new JPanel(); //include the product details
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));

        JLabel label1 = new JLabel("Select Product Category");

        displayLabel1 = new JLabel("Selected Product Details");

        int marginSize = 10;
        EmptyBorder emptyBorder = new EmptyBorder(marginSize, marginSize * 2, marginSize, 0);

        productIDLabel = new JLabel();
        categoryLabel = new JLabel();
        nameLabel = new JLabel();
        noItemsLabel = new JLabel();
        miscLabel1 = new JLabel();
        miscLabel2 = new JLabel();

        displayLabel1.setBorder(emptyBorder);
        productIDLabel.setBorder(emptyBorder);
        categoryLabel.setBorder(emptyBorder);
        nameLabel.setBorder(emptyBorder);
        noItemsLabel.setBorder(emptyBorder);
        miscLabel1.setBorder(emptyBorder);
        miscLabel2.setBorder(emptyBorder);


        String[] dropdownList = {"All", "Electronic", "Clothing"};
        JComboBox<String> dropdown = new JComboBox<>(dropdownList);
        dropdown.setPrototypeDisplayValue("XXXXXXXXXXXXXXXX");  //set a width in the dropbox

        selectedValue = (String) dropdown.getSelectedItem();

        shoppingCartButton = new JButton("Shopping Cart");
        addtocartbtn = new JButton("Add to Shopping Cart");

        addToCartPanel.add(Box.createVerticalGlue()); //align bottom left to the panel
        addToCartPanel.setBorder(new EmptyBorder(0, 0, marginSize * 6, 0));
        addToCartPanel.add(addtocartbtn);

        panel3.add(label1);
        panel4.add(dropdown);
        panel5.add(shoppingCartButton);


        panel2.add(panel3);
        panel2.add(panel4);
        panel2.add(panel5);

        displayPanel.add(displayLabel1);
        displayPanel.add(productIDLabel);
        displayPanel.add(categoryLabel);
        displayPanel.add(nameLabel);
        displayPanel.add(miscLabel1);
        displayPanel.add(miscLabel2);
        displayPanel.add(noItemsLabel);

//      Gets the information on mangers product list and creates the table
        table = new JTable(createTableModel(getProductDropdown(WestminsterShoppingManager.ManagerCart.getProductList(), selectedValue)));
        table.setDefaultEditor(Object.class, null);
        //adds the scrolling option to the table
        JScrollPane scrollPane = new JScrollPane(table);


        toppanel.add(panel2);
        toppanel.add(scrollPane);

        //when user select the category dropdown action listener display the relative category
        dropdown.addActionListener(e -> {
            selectedValue = (String) dropdown.getSelectedItem();

            //updates the table with the selected product category item
            DefaultTableModel newModel = createTableModel(getProductDropdown(WestminsterShoppingManager.ManagerCart.getProductList(), selectedValue));
            table.setModel(newModel);
        });


        mainFrame.add(toppanel);
        bottomPanel.add(displayPanel);

        bottomPanel.add(addToCartPanel);
        mainFrame.add(bottomPanel);


        //user click the row it will give product details
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int viewRow = table.getSelectedRow();
                    if (viewRow != -1) {
                        int modelRow = table.convertRowIndexToModel(viewRow); // Convert view index to model index
                        // Make sure the productList is the one currently being displayed
                        ArrayList<Product> currentProductList = getProductDropdown(WestminsterShoppingManager.ManagerCart.getProductList(), selectedValue);
                        if (modelRow < currentProductList.size()) { // Check if the model row is within the list size
                            selectedProduct = currentProductList.get(modelRow);
                            updateDisplayPanel(selectedProduct);
                        }
                    }
                }
            }
        });

        //if the user select items it will go to the add to cart
        addtocartbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedProduct != null){
                    Product producttobeadded = selectedProduct; //creates a dummy product variable to change no of items to 1
                    producttobeadded.setNumberofavailableItems(1);
                    usercart.addProduct(selectedProduct); //the product selected in table get added to the cart
                    try {
                        ShoppingCartFrame.updateInformation(); // Open the ShoppingCart Frame by clicking the shopping_cart_Button
                        ShoppingCartFrame.updateTableModel();
                    }catch (NullPointerException ignored){} //null pointer exception ignored
                }
                JOptionPane.showMessageDialog(null, "The items added to the cart", "Title", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new ShoppingCartFrame().updateInformation();
            }
        });
        mainFrame.setVisible(true);

    }



    //updates the display panel  table rows and columns according to the selected products
    private static void updateDisplayPanel(Product selectedProduct) {
        productIDLabel.setText("Product ID - " + selectedProduct.getProductId());
        categoryLabel.setText("Category - " + selectedProduct.getObjecttype());
        nameLabel.setText("Name - " + selectedProduct.getProductName());
        noItemsLabel.setText("Items available - " + selectedProduct.getNumberofavailableItems());

        switch (selectedProduct.getObjecttype()) { //update the information
            case "electronics":
                miscLabel1.setText("Brand - " + selectedProduct.getBrand());
                miscLabel2.setText("Warranty Period - " + selectedProduct.getWarrantyPeriod());
                break;
            case "clothing":
                miscLabel1.setText("Size - " + selectedProduct.getSize());
                miscLabel2.setText("Color - " + selectedProduct.getColor());
                break;
        }

    }



    //returns an arraylist with all the products in the selected product category
    private static ArrayList<Product> getProductDropdown(ArrayList<Product> productList, String selectedType) {
        ArrayList<Product> newproductList = new ArrayList<>();
        switch (selectedType) {
            case "All":
                newproductList = productList;
                break;
            case "Electronic":
                for (Product product : productList) {
                    if (product.getObjecttype().equals("electronics")) {
                        newproductList.add(product);
                    }
                }
                break;
            case "Clothing":
                for (Product product : productList) {
                    if (product.getObjecttype().equals("clothing")) {
                        newproductList.add(product);
                    }
                }
                break;
        }
        return newproductList;
    }

    //returns the table model to from the mangers product list
    private static DefaultTableModel createTableModel(ArrayList<Product> productList) {
        String[] columnNames = {"Product ID", "Name", "Category", "Price($)", "Info"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Product product : productList) { //creates the table information according to the category
            switch (product.getObjecttype()) {
                case "electronics":
                    Object[] rowDataElectronic = {product.getProductId(), product.getProductName(), product.getObjecttype(), product.getPrice(), product.getBrand() + ", " + product.getWarrantyPeriod() + " months warranty"};
                    model.addRow(rowDataElectronic);
                    break;
                case "clothing":
                    Object[] rowDataClothing = {product.getProductId(), product.getProductName(), product.getObjecttype(), product.getPrice(), product.getSize() + ", " + product.getColor()};
                    model.addRow(rowDataClothing);
                    break;
            }
        }
        return model;
    }
}





