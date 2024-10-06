package org.kanesweet;



public class App {

    GrpcServer server = new GrpcServer();
    GrpcClient client = new GrpcClient();

    public void test() {
        System.out.println("Starting...");
    }

    public static void main(String[] args) {
        try {
            App app = new App();
            app.server.start();
            app.client.start();
        } catch (Exception e) {
            System.err.println(e);
        }

    }
}
