package com.example;

import com.azure.messaging.servicebus.*;
import com.azure.core.credential.*;
import com.azure.identity.*;

import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    static String queueName = "myqueue";

    public static void main(String[] args) {
        System.out.println("Sending...");
        //sendMessage();
        System.out.println("Message Sent!");
    }

    static void sendMessage() {

        TokenCredential workloadIdentityCredential = new WorkloadIdentityCredentialBuilder()
     .clientId("eeba643d-b3e7-4790-9c4a-7a0658bc50cb")
     .tenantId("74362cdd-5ab8-4373-83cc-ef21a71605c8")
     .tokenFilePath("<token-file-path>")
     .build();

        // create a token using the default Azure credential
        // DefaultAzureCredential credential = new DefaultAzureCredentialBuilder()
        //         .build();

        ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
                .fullyQualifiedNamespace("sbarra.servicebus.windows.net")
                .credential(credential)
                .sender()
                .queueName(queueName)
                .buildClient();

        // send one message to the queue
        senderClient.sendMessage(new ServiceBusMessage("Hello, World!"));
        System.out.println("Sent a single message to the queue: " + queueName);
    }




    
}
