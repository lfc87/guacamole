import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;

public class guacamole {

    public static void main(String[] args) throws Exception {

        System.out.println("Start-HTTP-HelloFrodo");
        print(System.out);

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/hello", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {}

        server.stop(1);
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "HelloFrodo is online";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    public static void print(PrintStream out) {
        out.println("HelloFrodo-Test");
    }
}