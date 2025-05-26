/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.log.in;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author aspir
 */
public class MessageIT {
    
    public MessageIT() {
    }

    @Test
    void testCheckMessageLength_Success() {
        Message msg = new Message("0123456789", "Valid message under 250 chars", 1);
        assertEquals("Message ready to send.", msg.checkRecipientCell());
    }

    @Test
    void testCheckMessageLength_Failure() {
        String longMessage = "a".repeat(300);
        Message msg = new Message("0123456789", longMessage, 2);
        assertEquals("Message exceeds 250 characters by 50, please reduce size.",
                msg.checkRecipientCell());
    }

    @Test
    void testCheckRecipientFormat_Success() {
        Message msg = new Message("+27718693002", "Hello", 3);
        assertEquals("Cell phone number successfully captured.",
                msg.checkRecipientCell());
    }

    @Test
    void testCheckRecipientFormat_Failure() {
        Message msg = new Message("0657397889", "Hello", 4);
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                msg.checkRecipientCell());
    }
}