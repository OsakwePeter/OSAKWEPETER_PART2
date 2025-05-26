/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.log.in;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author aspir
 */
public class Message {

    public String messageId;
    public String recipient;
    public String messageContent;
    public int messageNumber;
    public String messageHash;
    public static List<Message> sentMessages = new ArrayList<>();
    public static int totalMessagesSent = 0;
    
    
    public Message(String recipient, String messageContent, int messageNumber) {
        this.messageId = generateMessageId();
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageNumber = messageNumber;
        this.messageHash = createMessageHash();
    }

    public String generateMessageId() {
        Random rand = new Random();
        long id = 1000000000L + rand.nextInt(900000000);
        return String.valueOf(id);
    }

    public int checkRecipientCell() {
    if (recipient == null || recipient.isEmpty()) {
        return 0;
    }
    if (recipient.length() != 10 || !recipient.matches("\\d{10}")) {
        return 0;
    }
    return -1;
}

    public String createMessageHash() {
        String[] words = messageContent.split("\\s+");
        String first = words.length > 0 ? words[0] : "";
        String last = words.length > 0 ? words[words.length - 1] : "";
        return messageId.substring(0, 2) + ":" + messageNumber + ":" + first.toUpperCase() + last.toUpperCase();
    }

    public String sentMessage() {
        String[] options = {"Send Message", "Disregard Message", "Store Message to send later"};
        int choice = JOptionPane.showOptionDialog(null, "What would you like to do with this message?",
                "Message Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        switch (choice) {
            case 0: return "send";
            case 1: return "disregard";
            case 2: return "store";
            default: return "disregard";
        }
    }

    public String printMessages() {
        return "Message ID: " + messageId + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageContent;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("messageId", messageId);
        json.put("recipient", recipient);
        json.put("messageContent", messageContent);
        json.put("messageNumber", messageNumber);
        json.put("messageHash", messageHash);
        return json;
    }
      public static boolean verifyLogin() {
        // Implement your login verification logic
        // This could check username/password, etc.
        return true; // Simplified for example
    }
    public static void sendMessagesFlow() {
        String numMessagesStr = JOptionPane.showInputDialog("How many messages would you like to send?");
        int numMessages;

        try {
            numMessages = Integer.parseInt(numMessagesStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number.");
            return;
        }

        JSONArray messagesJson = new JSONArray();
        int successfullySent = 0;

        for (int i = 0; i < numMessages; i++) {
            String recipient = JOptionPane.showInputDialog("Enter recipient cell number:");
            String content = JOptionPane.showInputDialog("Enter message (max 250 characters):");

            if (content.length() > 250) {
                JOptionPane.showMessageDialog(null, "Message too long.");
                i--;
                continue;
            }

            Message msg = new Message(recipient, content, totalMessagesSent + 1);

            if (msg.checkRecipientCell() != 0) {
                JOptionPane.showMessageDialog(null, "Invalid recipient.");
                i--;
                continue;
            }

            String action = msg.sentMessage();
            if ("send".equals(action)) {
                JOptionPane.showMessageDialog(null, msg.printMessages());
                sentMessages.add(msg);
                totalMessagesSent++;
                successfullySent++;
                messagesJson.put(msg.toJson());
            } else if ("store".equals(action)) {
                messagesJson.put(msg.toJson());
            }
        }

        storeMessagesToFile(messagesJson);

        JOptionPane.showMessageDialog(null,
            "Sending complete.\n" +
            "Sent this session: " + successfullySent + "\n" +
            "Total sent: " + totalMessagesSent);
    }

    public static void storeMessagesToFile(JSONArray messagesJson) {
        try (FileWriter file = new FileWriter("messages.json")) {
            file.write(messagesJson.toString());
            file.flush();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to save messages.");
        }
    }
}


