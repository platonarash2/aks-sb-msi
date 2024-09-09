package com.example;

import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.identity.*;

import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 */
//public final class App {

public final class App {
     private  App() {

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {

        // System.out.println("Hello World!");

       // static String queueName = "<QUEUE NAME>";

    }

   
    //  // Correct usage of void as a return type
    //  public void myMethod() {
    //     System.out.println("This method does not return anything.");
    // }



    // public static void sendMessage(String queueName) {
    //     // create a token using the default Azure credential
    //     DefaultAzureCredential credential = new DefaultAzureCredentialBuilder()
    //             .build();

    //     ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
    //             .fullyQualifiedNamespace("NAMESPACENAME.servicebus.windows.net")
    //             .credential(credential)
    //             .sender()
    //             .queueName(queueName)
    //             .buildClient();

    //     // send one message to the queue
    //     senderClient.sendMessage(new ServiceBusMessage("Hello, World!"));
    //     System.out.println("Sent a single message to the queue: " + queueName);
    // }

    
     }
}

