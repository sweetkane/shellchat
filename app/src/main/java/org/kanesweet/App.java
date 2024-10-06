package org.kanesweet;



public class App {
    public static void main(String[] args) {
        try {
            GrpcServer server = new GrpcServer();
            GrpcClient client = new GrpcClient();
            server.start();
            client.start();
        } catch (Exception e) {
            System.err.println(e);
        }

    }
}
