package org.kanesweet;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            GrpcServer server = new GrpcServer(scanner);
            GrpcClient client = new GrpcClient(scanner);
            server.start();
            client.start();
            server.join();
            client.join();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            scanner.close();
        }

    }
}
