package org.kanesweet;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.net.URL;

public class GrpcServer extends Thread {

    Server server;
    int port;

    GrpcServer() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Port: ");
            port = scanner.nextInt();

            // Build and start the gRPC server
            server = ServerBuilder.forPort(port)
            .addService(new ChatServiceImpl()) // Your service implementation
            .build();
            server.start();
            printServerAddress(port);

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

    private static void printServerAddress(int port) {
        try {
            // Get the IP address of the server
            String ip = getIp();

            // Print out the address and port
            System.out.println("Running on: " + ip + ":" + port);

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static String getIp() throws Exception {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            String ip = in.readLine();
            return ip;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
