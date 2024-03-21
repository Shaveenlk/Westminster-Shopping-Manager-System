package org.CourseWork.Shopping_Cart_GUI;//import Frames.WestminsterShoppingCenter;


import org.CourseWork.Shopping_Cart_Console.Product;
import org.CourseWork.Shopping_Cart_Console.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ShoppingCartFrame {
    JFrame shoppingCartFrame;
    static JTable shoppingCartTable;
    private static JLabel totalLabel, discountLabel, sameitemLabel, FinaltotalLabel,totalmisclabel,discountmisclabel,sameitemmisclabel,Finaltotalmisclabel;

    JPanel  informationpanel,infopanel1,infopanel2;
    private static JScrollPane scrollPane;


    public ShoppingCartFrame(){
        shoppingCartFrame = new JFrame("Shopping Cart");
        shoppingCartFrame.setSize(800, 600);

        shoppingCartFrame.setLayout(new GridLayout(2,1)); //table and bill information


        int marginSize = 7;
        EmptyBorder emptyBorder = new EmptyBorder(marginSize*4, marginSize*4, marginSize*4, marginSize*4);

        shoppingCartTable =  createJTable(ShoppingGUI.usercart.getProductList());
        scrollPane = new JScrollPane(shoppingCartTable);
        scrollPane.setBorder(emptyBorder);

        shoppingCartFrame.add(scrollPane);
        shoppingCartFrame.setVisible(true);


        shoppingCartTable.getModel().addTableModelListener(e -> { //user can edit the quantity
            int row = e.getFirstRow();
            int column = e.getColumn();

            if (column == 1) { // Check if the edited column is the Quantity column
                DefaultTableModel model1 = (DefaultTableModel) shoppingCartTable.getModel();
                Object quantity =  model1.getValueAt(row, column);

                // Get the relative Product object and update the quantity using the setter method
                Product product = ShoppingGUI.usercart.getProductList().get(row);
                product.setNumberofavailableItems(Integer.parseInt(quantity.toString()));
                updateTableModel();
                updateInformation();
            }
        });

        informationpanel =new JPanel(new GridLayout(1,2)); //Includeds billing information and detals
        infopanel1=new JPanel(); //includes the labels
        infopanel2 = new JPanel(); //includes the labels with values

        infopanel1.setLayout(new BoxLayout(infopanel1, BoxLayout.Y_AXIS)); //box layout to list down labels to y axis
        infopanel2.setLayout(new BoxLayout(infopanel2, BoxLayout.Y_AXIS));

        informationpanel.add(infopanel1);
        informationpanel.add(infopanel2);

        totalLabel =new JLabel();
        discountLabel =new JLabel();
        sameitemLabel =new JLabel();
        FinaltotalLabel=new JLabel();

        totalmisclabel =new JLabel();
        discountmisclabel =new JLabel();
        sameitemmisclabel =new JLabel();
        Finaltotalmisclabel=new JLabel();


        totalLabel.setBorder(emptyBorder);
        discountLabel.setBorder(emptyBorder);
        sameitemLabel.setBorder(emptyBorder);
        FinaltotalLabel.setBorder(emptyBorder);

        totalmisclabel.setBorder(emptyBorder);
        discountmisclabel.setBorder(emptyBorder);
        sameitemmisclabel.setBorder(emptyBorder);
        Finaltotalmisclabel.setBorder(emptyBorder);


        totalLabel.setText("Total       -");
        discountLabel.setText("First Purchase Discount(10%)     -");
        sameitemLabel.setText("Three items in same Category Discount(20%)       -");
        FinaltotalLabel.setText("Final Total");


        infopanel1.add(totalLabel);
        infopanel1.add(discountLabel);
        infopanel1.add(sameitemLabel);
        infopanel1.add(FinaltotalLabel);

        infopanel2.add(totalmisclabel);
        infopanel2.add(discountmisclabel);
        infopanel2.add(sameitemmisclabel);
        infopanel2.add(Finaltotalmisclabel);


        shoppingCartFrame.add(informationpanel);
        shoppingCartFrame.setVisible(true);
    }



    public JTable createJTable(ArrayList<Product> productList){ //creates table for shopping cart
        String[] columnNames = {"Product" , "Quantity", "Price($)"};
        DefaultTableModel model = new DefaultTableModel(columnNames,0);

        for (Product product : productList) {
            switch (product.getObjecttype()){
                case "electronics":
                    Object[] rowDataElectronic = {
                            product.getProductId() +" "+product.getProductName()+" "+product.getBrand()+" "+product.getWarrantyPeriod(),
                            product.getNumberofavailableItems(),
                            product.getPrice(),
                    };
                    model.addRow(rowDataElectronic);
                    break;
                case "clothing":
                    Object[] rowDataClothing = {
                            product.getProductId() +" "+product.getProductName()+" "+product.getSize()+" "+product.getColor(),
                            product.getNumberofavailableItems(),
                            product.getPrice(),
                    };
                    model.addRow(rowDataClothing);
                    break;
            }
        }

        JTable table = new JTable(model);
        return  table;
    }



    //updates the billing information
    public static void updateInformation(){
        totalmisclabel.setText(ShoppingGUI.usercart.totalCost()+"$");
        discountmisclabel.setText(ShoppingGUI.usercart.firstDiscount(User.newUserAccount)+"$");
        sameitemmisclabel.setText(ShoppingGUI.usercart.category_Discount()+"$");
        Finaltotalmisclabel.setText(ShoppingGUI.usercart.finaltotal()+"$");
    }


    public static void updateTableModel() { //update the information on table which user input
        DefaultTableModel model = (DefaultTableModel) shoppingCartTable.getModel();
        model.setRowCount(0);
        ArrayList<Product> productList = ShoppingGUI.usercart.getProductList();

        for (Product product : productList) {
            switch (product.getObjecttype()) {
                case "electronics":
                    Object[] rowDataElectronic = {
                            product.getProductId() + " " +
                                    product.getProductName() + " " +
                                    product.getBrand() + ", " + product.getWarrantyPeriod(),
                            product.getNumberofavailableItems(),
                            product.getPrice()*product.getNumberofavailableItems()};
                    model.addRow(rowDataElectronic);
                    break;
                case "clothing":
                    Object[] rowDataClothing = {
                            product.getProductId() + " " +
                                    product.getProductName() + " " +
                                    product.getSize() + ", " + product.getColor(),
                            product.getNumberofavailableItems(),
                            product.getPrice()*product.getNumberofavailableItems(),
                    };
                    model.addRow(rowDataClothing);
                    break;
            }
        }
    }
}
