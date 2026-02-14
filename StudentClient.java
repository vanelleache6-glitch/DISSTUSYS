import java.io.*;
import java.net.*;
import java.util.Scanner;

public class StudentClient {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;

        try (
            Socket socket = new Socket(host, port);
            BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(
                socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
        ) {

            System.out.println("Connected to server.");
            System.out.println("Commands: ADD, GET, LIST, EXIT");

            while (true) {
                System.out.print("Enter command: ");
                String command = scanner.nextLine();

                if (command.equalsIgnoreCase("EXIT")) {
                    break;
                }

                output.println(command);

                String response;
                if ((response = input.readLine()) != null) {
                    System.out.println("Server: " + response);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}