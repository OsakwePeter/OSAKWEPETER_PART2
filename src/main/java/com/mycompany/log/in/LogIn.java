/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.log.in;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author aspir
 */
public class LogIn {
public static String Firstname;
public static String Lastname;
public static String Username;
public static String Password;
public static String Phonenumber;
public static String registeredUsername;
public static String registeredPassword;
public static boolean loginsuccessfully = false;

    // Method to check username format
    public static boolean checkUserName(String Username) {
        return Username.contains("_") && Username.length() <= 5;
    }

    // Method to check password complexity
    public static boolean checkPasswordComplexity(String Password) {
        // Password must be at least 8 characters, with capital letter, number, and special char
        String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Password);
        return matcher.matches();
    }

    // Method to check cell phone number format
public static boolean checkCellPhoneNumber(String cellNumber){
    boolean hasnumber = cellNumber.matches("^\\+\\d{2,7}\\d{9}$");
    if(hasnumber){
        return true;
    }
    else{
        return false;
    }
}

    // Method to register user with validation
    public static String registerUser(String Firstname, String Lastname, String Username, 
                             String Password, String Phonenumber) {
        
        if (!checkUserName(Username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(Password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(Phonenumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
}

        registeredUsername = Username;
        registeredPassword = Password;
        return "User registered succesfully.";
}

    // Method to verify login credentialss
 public boolean loginUser(String username, String password) {
        if (!loginsuccessfully) {
            return false;
        }
        return this.Username.equals(username) && this.Password.equals(password);
    }

    // Method to return login status message
public static String returnLoginStatus(){
if(loginsuccessfully){
            return "Welcome " + Firstname +""+ Lastname + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
}

    // Getters for testing purposes
    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getCellNumber() {
        return Phonenumber;
    }
}
    