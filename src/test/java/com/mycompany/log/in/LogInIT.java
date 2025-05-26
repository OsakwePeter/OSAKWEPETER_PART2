/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.log.in;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author aspir
 */
public class LogInIT {
    
    public LogInIT() {
    }

    /**
     * Test of checkUserName method, of class LogIn.
     */
    @Test
    public void testCheckUserName_Valid() {
        assertTrue(LogIn.checkUserName("kyl_1"));
    }
    @Test
    public void testCheckUserName_Invalid() {
        assertFalse(LogIn.checkUserName("kyle!!!!!!!"));
    }

    /**
     * Test of checkPasswordComplexity method, of class LogIn.
     */
    @Test
    public void testCheckPasswordComplexity_Valid() {
        assertTrue(LogIn.checkPasswordComplexity("Ch&&sec@ke99!"));
    }
    
    @Test
    public void testCheckPasswordComplexity_InValid() {
        assertFalse(LogIn.checkPasswordComplexity("password"));
    }

    /**
     * Test of checkCellPhoneNumber method, of class LogIn.
     */
    @Test
    public void testCheckCellPhoneNumber_Valid() {
        assertTrue(LogIn.checkCellPhoneNumber("+27123456789"));
    }

    @Test
    public void testCheckCellPhoneNumber_InValid() {
        assertFalse(LogIn.checkCellPhoneNumber("08966553"));
    }

    /**
     * Test of registerUser method, of class LogIn.
     */
    @Test
    public void testRegisterUser_Valid() {
        String result = LogIn.registerUser("John", "Doe", "jd_01", "Strong1!", "+27123456789");
        assertEquals("User registered succesfully.", result);
        assertEquals("kyl_1", LogIn.registeredUsername);
        assertEquals("Ch&&sec@ke99!", LogIn.registeredPassword);
    }
    
    @Test
    public void testRegisterUser_Invalid() {
    // Invalid Username
    String resultInvalidUsername = LogIn.registerUser("Peter", "Osakwe", "kyle!!!!!!!", "Ch&&sec@ke99!", "+27123456789");
    assertTrue(resultInvalidUsername.contains("Username is not correctly formatted"),
        "Should detect improperly formatted username");

    // Invalid Password
    String resultInvalidPassword = LogIn.registerUser("Peter", "Osakwe", "kyl_1", "password", "+27123456789");
    assertTrue(resultInvalidPassword.contains("Password is not correctly formatted"),
        "Should detect weak password");

    // Invalid Phone Number
    String resultInvalidPhone = LogIn.registerUser("Peter", "Osakwe", "kyl_1", "Ch&&sec@ke99!", "08966553");
    assertTrue(resultInvalidPhone.contains("Cell phone number incorrectly formatted"),
        "Should detect invalid phone number format");
}


    /**
     * Test of loginUser method, of class LogIn.
     */
    @Test
    public void testLoginUser_Valid() {
        LogIn obj = new LogIn();
        LogIn.Username = "kyl_1";
        LogIn.Password = "Ch&&sec@ke99!";
        LogIn.loginsuccessfully = true;
        assertTrue(obj.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginUser_InValid() {
        LogIn obj = new LogIn();
        LogIn.Username = "kyl_1";
        LogIn.Password = "Ch&&sec@ke99!";
        LogIn.loginsuccessfully = true;
        assertFalse(obj.loginUser("kyle!!!!!!!", "password"));
    }
    
    /**
     * Test of returnLoginStatus method, of class LogIn.
     */
    @Test
    public void testReturnLoginStatus_Valid() {
        LogIn.Firstname = "Peter";
        LogIn.Lastname = "Osakwe";
        LogIn.loginsuccessfully = true;
        String status = LogIn.returnLoginStatus();
        assertTrue(status.contains("Welcome PeterOsakwe"));
    }

    @Test
    public void testReturnLoginStatus_InValid() {
        LogIn.loginsuccessfully = false;
        String status = LogIn.returnLoginStatus();
        assertEquals("Username or password incorrect, please try again.", status);
    }
    
}
