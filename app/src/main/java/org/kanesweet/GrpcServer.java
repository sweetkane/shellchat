package org.kanesweet;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.util.Scanner;

public class GrpcServer extends Thread {

    Server server;
    int port;

    GrpcServer(Scanner scanner) {
        try {
            System.out.print("Port: ");
            port = scanner.nextInt();

            server = ServerBuilder.forPort(port)
            .addService(new ChatServiceImpl())
            .build();
            server.start();

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void run() {
        try {
            server.awaitTermination();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
