package org.CourseWork.Shopping_Cart_Console;

public class User {
    private String username;
    private String password;

    //used which checking and calculating first purchase discount
    public static boolean newUserAccount = false;

    //userdetails array
    String data[][] ={{"nimal","1234"},{"amal","$k90"},{"namal","9090"}};

    public void storeDatabase(){
        newUserAccount = true;

        for (String[] userCredentials : data) {
            String usernameArray = userCredentials[0];
            String passwordArray = userCredentials[1];

            if (getUsername().equals(usernameArray) && getPassword().equals(passwordArray)) {
                newUserAccount = false;
                break;
            }
        }
        if (newUserAccount) {
            System.out.println("Hello new User!");
        } else {
            System.out.println("Login successful");
        }
    }


    //add getters for username and password
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    //adding setters for username and password
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
