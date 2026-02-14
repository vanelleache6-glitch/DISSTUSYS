import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandler implements Runnable {

    private Socket socket;
    private Map<String, Student> database;

    public ClientHandler(Socket socket, Map<String, Student> database) {
        this.socket = socket;
        this.database = database;
    }

    public void run() {
        try (
            BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(
                socket.getOutputStream(), true);
        ) {

            String request;

            while ((request = input.readLine()) != null) {

                System.out.println("[SERVER] Request received: " + request);

                String[] parts = request.split(" ");
                String command = parts[0];

                switch (command) {

                    case "ADD":
                        if (parts.length >= 4) {
                            String id = parts[1];
                            String course = parts[2];
                            String name = parts[3];

                            Student student = new Student(id, course, name);
                            database.put(id, student);

                            output.println("Student added successfully");
                        } else {
                            output.println("Invalid ADD format");
                        }
                        break;

                    case "GET":
                        if (parts.length == 2) {
                            String id = parts[1];
                            Student student = database.get(id);

                            if (student != null)
                                output.println(student.toString());
                            else
                                output.println("Student not found");
                        }
                        break;

                    case "LIST":
                        if (database.isEmpty()) {
                            output.println("No students available");
                        } else {
                            for (Student s : database.values()) {
                                output.println(s.toString());
                            }
                        }
                        break;

                    default:
                        output.println("Unknown command");
                }
            }

        } catch (IOException e) {
            System.out.println("[SERVER] Client disconnected.");
        }
    }
}