import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClientTest {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Replace with the IP or hostname of the server
        int serverPort = 1234; // Replace with the port number of the server

        try {
            // Create a socket and connect to the server
            Socket socket = new Socket(serverAddress, serverPort);

            // Get the output stream to send data to the server
            OutputStream outputStream = socket.getOutputStream();

            // Send a message to the server
            String message = "Hello, server!";
            byte[] messageBytes = message.getBytes();
            outputStream.write(messageBytes);

            // Close the socket when done
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
