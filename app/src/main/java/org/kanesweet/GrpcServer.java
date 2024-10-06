package org.kanesweet;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.util.Scanner;

public class GrpcServer extends Thread {

    Server server;
    int port;

    GrpcServer() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Port: ");
            port = scanner.nextInt();
            scanner.close();

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

    // private static void printServerAddress(int port) {
    //     try {
    //         // Get the IP address of the server
    //         String ip = getIp();

    //         // Print out the address and port
    //         System.out.println("Running on: " + ip + ":" + port);

    //     } catch (Exception e) {
    //         System.err.println(e);
    //     }
    // }

    // public static String getIp() throws Exception {
    //     URL whatismyip = new URL("http://checkip.amazonaws.com");
    //     BufferedReader in = null;
    //     try {
    //         in = new BufferedReader(new InputStreamReader(
    //                 whatismyip.openStream()));
    //         String ip = in.readLine();
    //         return ip;
    //     } finally {
    //         if (in != null) {
    //             try {
    //                 in.close();
    //             } catch (IOException e) {
    //                 e.printStackTrace();
    //             }
    //         }
    //     }
    // }
}
