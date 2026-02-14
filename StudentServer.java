import java.io.*;
import java.net.*;
import java.util.*;

public class StudentServer {

    private static Map<String, Student> studentDatabase =
        Collections.synchronizedMap(new HashMap<>());

    public static void main(String[] args) {
        int port = 5000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[SERVER] Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("[SERVER] New client connected");

                ClientHandler handler = new ClientHandler(clientSocket, studentDatabase);
                new Thread(handler).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}