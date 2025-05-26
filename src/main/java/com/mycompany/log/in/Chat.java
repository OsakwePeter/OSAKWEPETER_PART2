/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.log.in;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author aspir
 */
public class Chat {
    static Scanner msg = new Scanner(System.in);
    public static List<Message> sentMessages = new ArrayList<>();
    public static int totalMessagesSent = 0;
    public static String Firstname;
    public static String Lastname;
    public static String Username;
    public static String Password;
    public static String Phonenumber;
    public static String registeredUsername;
    public static String registeredPassword;
    public static boolean loginsuccessfully = false;
   
    
    public static void main(String[] args) {
    System.out.println("Enter your firstname");
    Firstname = msg.nextLine();
    LogIn.Firstname = Firstname;
    
    System.out.println("Enter your lastname");
    Lastname = msg.nextLine();
    LogIn.Lastname = Lastname;
    while(true){
    System.out.println("Enter your username");
    Username = msg.nextLine();
    LogIn.Username = Username;    
    
        if(LogIn.checkUserName(Username)){
        System.out.println("Username successfully captured.");
        break;
        }
        else{
        System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.");
        }
    }
    while(true){
    System.out.println("Enter your password");
    Password = msg.nextLine();
    LogIn.Password = Password;
    
        if(LogIn.checkPasswordComplexity(Password)){
        System.out.println("Password successfully captured.");
        break;
        }
    else{
        System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
    }
    }
    while(true){
    System.out.println("Enter your phone number");
    Phonenumber = msg.nextLine();
    LogIn.Phonenumber = Phonenumber;
    
    
    if(LogIn.checkCellPhoneNumber(Phonenumber)){
        System.out.println("Cell phone number successfully added.");
        break;
    }
    else{
        System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
    }
    }
        boolean loggedin = false;

        // Login Attempt
        String LoginUser =  LogIn.registerUser(Firstname, Lastname, Username, Password, Phonenumber);
        System.out.println(LoginUser);
        
        System.out.println("\n--- Login ---");
        while(!loggedin){
        System.out.println("Enter username to login: ");
        LoginUser = msg.nextLine();
        System.out.println("Enter password to login: ");
        LoginUser = msg.nextLine();

        if (Username.equals(LogIn.registeredUsername) && Password.equals(LogIn.registeredPassword)) {
        LogIn.loginsuccessfully = true;
        System.out.println(LogIn.returnLoginStatus());
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");
        loggedin = true;
    } else {
        System.out.println(LogIn.returnLoginStatus());
    }
}
        boolean running = true;
        while (running) {
            String choice = JOptionPane.showInputDialog(
                "Please choose an option:\n" +
                "1) Send Messages\n" +
                "2) Show recently sent messages\n" +
                "3) Quit");

            switch (choice) {
                case "1":
                    Message.sendMessagesFlow();
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Feature coming soon.");
                    break;
                case "3":
                    running = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice.");
        }      
        }
        
        }

    
        }
    
