package org.kanesweet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.kanesweet.chat.*;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class GrpcClient extends Thread {

    private ManagedChannel channel;
    private ChatServiceGrpc.ChatServiceBlockingStub blockingStub;
    private String username;
    private String recipientAddress;

    public void connect(String address) {
        channel = ManagedChannelBuilder.forTarget(address)
                .usePlaintext()  // For simple plaintext connection (no TLS)
                .build();
        blockingStub = ChatServiceGrpc.newBlockingStub(channel);
    }

        public void sendMessage(String message) {
        Chat chat = Chat.newBuilder()
                .setUsername(username)
                .setMessage(message)
                .build();

        try {
            blockingStub.sendChat(chat);
        } catch (StatusRuntimeException e) {
            System.out.println("RPC failed: " + e.getStatus());
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("User name: ");
        username = scanner.next();
        System.out.println("Recipient address: ");
        recipientAddress = scanner.next();

        connect(recipientAddress);

        while (true) {
            String message = scanner.next();
            sendMessage(message);
        }
    }
}
