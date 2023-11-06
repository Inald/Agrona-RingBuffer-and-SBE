import com.baeldung.sbe.stub.TradeDataEncoder;
import org.agrona.ExpandableArrayBuffer;
import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.UnsafeBuffer;
import org.agrona.concurrent.ringbuffer.ManyToOneRingBuffer;
import org.agrona.concurrent.ringbuffer.RingBuffer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ConnectionAcceptor {

    public ConnectionAcceptor(final int portNumber, final ManyToOneRingBuffer ringBuffer) {
        try {
            Thread clientHandler = new Thread(new ClientHandler(ringBuffer, portNumber));
            clientHandler.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Socket acceptSocket(int portNumber) throws IOException {
        ServerSocket serverSocket = new ServerSocket(portNumber);

        System.out.println("Server is listening on port " + portNumber);

        Socket clientSocket = serverSocket.accept();
        return clientSocket;
    }

    class ClientHandler implements Runnable {
        private final ManyToOneRingBuffer ringBuffer;
        MutableDirectBuffer mutableDirectBuffer;
        private final TradeDataCodec tradeDataCodec;
        private final TradeData trade = new TradeData();
        public static final int MSG_TYPE_TRADE = 1;
        final int portNumber;

        public ClientHandler(final ManyToOneRingBuffer ringBuffer, final int portNumber) {
            this.ringBuffer = ringBuffer;
            this.mutableDirectBuffer = new ExpandableArrayBuffer(1024);
            this.tradeDataCodec = new TradeDataCodec();
            this.portNumber = portNumber;
        }

        @Override
        public void run() {
            try {
                Socket clientSocket;
                clientSocket = acceptSocket(portNumber);
                while (true) {
                    handleRead(clientSocket);
                }
//                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void handleRead(Socket clientSocket) throws IOException {
            // Get the input and output streams for communication

            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            // Read the message sent by the client
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);

            if (bytesRead > 0) {
                String receivedMessage = new String(buffer, 0, bytesRead);

                // Print the received message to the console
                System.out.println("Received message from client: " + receivedMessage);

                trade.reset();
                trade.setTicker(receivedMessage);
                int encode_length = tradeDataCodec.encode(mutableDirectBuffer, trade);
                System.out.println("encode length: " + encode_length);
                ringBuffer.write(MSG_TYPE_TRADE, mutableDirectBuffer, 0, encode_length + 8);// might not be this
            }
        }
    }
}
