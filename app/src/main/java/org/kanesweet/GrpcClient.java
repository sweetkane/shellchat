package org.kanesweet;

import java.util.Scanner;
import org.kanesweet.chat.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class GrpcClient extends Thread {

    Scanner scanner;
    private ManagedChannel channel;
    private ChatServiceGrpc.ChatServiceBlockingStub blockingStub;
    private String username;
    private String recipientAddress;

    GrpcClient(Scanner scannerIn) {
        scanner = scannerIn;

        System.out.print("User name: ");
        username = scanner.next();
        System.out.print("Recipient address: ");
        recipientAddress = scanner.next();

        connect(recipientAddress);
        System.out.println("Connected! Starting chat...\n===========================");
        scanner.nextLine();
    }

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
        while (true) {
            String message = scanner.nextLine();
            if (message == "_exit") break;
            sendMessage(message);
        }
    }
}
