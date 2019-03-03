import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;

// RUN: ./gradlew build
// RUN: java -cp jarFile className
// RUN: java -cp ./build/libs/sample.project-1.0-SNAPSHOT.jar HelloFrodo

public class HelloFrodo {

    public static void main(String[] args) throws Exception {

        System.out.println("Start-HTTP-HelloFrodo");
        print(System.out);

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/hello", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
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
