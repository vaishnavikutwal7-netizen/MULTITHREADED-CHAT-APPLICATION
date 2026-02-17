import java.io.*;
import java.net.Socket;

public class ChatClient {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 5000);

            BufferedReader input =
                    new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out =
                    new PrintWriter(socket.getOutputStream(), true);

            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            String message;

            while ((message = input.readLine()) != null) {
                out.println(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
