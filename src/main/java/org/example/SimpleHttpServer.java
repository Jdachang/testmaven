package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleHttpServer {

    public static void main(String[] args) throws IOException {
        // 创建一个 HttpServer 实例，监听在指定端口
        HttpServer server = HttpServer.create(new InetSocketAddress(5000), 0);

        // 创建上下文路径并设置处理程序
        server.createContext("/", new MyHandler());

        // 设置服务器的执行器为 null，使用默认的执行器
        server.setExecutor(null);

        // 启动服务器
        server.start();
    }

    // 自定义处理程序
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // 获取输出流
            OutputStream os = exchange.getResponseBody();

            // 发送响应头，必须在 sendResponseHeaders 前调用
            exchange.sendResponseHeaders(200, 0);

            // 发送响应体
            String response = "Hello, World3!";
            os.write(response.getBytes());

            // 关闭输出流和交换
            os.close();
            exchange.close();
        }
    }
}
