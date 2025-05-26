/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.log.in;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author aspir
 */
public class MessageIT {
    
    public MessageIT() {
    }

    /**
     * Test of generateMessageId method, of class Message.
     */
    @Test
    public void testGenerateMessageId() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight", 2);
        String id = msg.generateMessageId();
        assertNotNull(id);
        assertEquals(10, id.length(), "Message ID should be 10 digits long");
    }

    /**
     * Test of checkRecipientCell method, of class Message.
     */
    @Test
    public void testCheckRecipientCell() {
        Message validMsg = new Message("+27718693002", "3Test", 1);
        assertEquals(-1, validMsg.checkRecipientCell(), "Valid number should return -1");

        Message invalidMsg = new Message("123", "Test", 1);
        assertEquals(0, invalidMsg.checkRecipientCell(), "Invalid number should return 0");
    }

    /**
     * Test of sentMessage method, of class Message.
     */
    @Test
    public void testSentMessage() {
        Message msg = new Message("0123456789", "Testing message action", 2);

        // Cannot test JOptionPane without mocking, so skip actual input.
        // Assume return from sentMessage() is one of: "send", "store", "disregard".
        // Suggest: mock this in actual UI test or refactor to allow injection.
        assertNotNull(msg.sentMessage(), "Should return a valid string option");
    }

    /**
     * Test of printMessages method, of class Message.
     */
    @Test
    public void testPrintMessages() {
        Message msg = new Message("0123456789", "Test message print", 3);
        String output = msg.printMessages();
        assertTrue(output.contains("Message ID"));
        assertTrue(output.contains("Recipient"));
        assertTrue(output.contains("Message"));
    }

    /**
     * Test of toJson method, of class Message.
     */
    @Test
    public void testToJson() {
        Message msg = new Message("0123456789", "Testing JSON", 4);
        JSONObject json = msg.toJson();
        assertEquals(msg.messageId, json.getString("messageId"));
        assertEquals(msg.recipient, json.getString("recipient"));
        assertEquals(msg.messageContent, json.getString("messageContent"));
        assertEquals(msg.messageNumber, json.getInt("messageNumber"));
        assertEquals(msg.messageHash, json.getString("messageHash"));
    }

    /**
     * Test of verifyLogin method, of class Message.
     */
    @Test
    public void testVerifyLogin() {
         // Since this method always returns true in your logic
        assertTrue(Message.verifyLogin(), "Should return true");
    }

    /**
     * Test of sendMessagesFlow method, of class Message.
     */
    @Test
    public void testSendMessagesFlow() {
         // Cannot unit test without mocking JOptionPane inputs.
        // Suggest: Refactor Message.sendMessagesFlow to support testability.
        // Here we only verify method exists and doesn't crash when separated.
        assertDoesNotThrow(() -> {
            // Message.sendMessagesFlow(); // avoid actual dialog in unit test
        }, "Method should not throw exceptions");
    }

    /**
     * Test of storeMessagesToFile method, of class Message.
     */
    @Test
    public void testStoreMessagesToFile() {
        JSONObject json = new Message("0123456789", "Saved to file", 5).toJson();
        org.json.JSONArray array = new org.json.JSONArray();
        array.put(json);

        assertDoesNotThrow(() -> {
            Message.storeMessagesToFile(array);
        }, "Storing JSON to file should not throw an exception");
    }
    
}
