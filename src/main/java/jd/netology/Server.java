package jd.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int LOCALHOST_PORT = 8081;

    public static void main(String[] args) {
// порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080
        try (ServerSocket serverSocket = new ServerSocket(LOCALHOST_PORT)) {
            System.out.println("Сервер стартовал");
            while (true) {
                try (Socket clientSocket = serverSocket.accept(); // ждем подключения
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    System.out.println("New connection accepted");
                    out.println("Write your name");
                    String name = in.readLine();
                    out.println("What color of night?");
                    String response = in
                            .readLine()
                            .toLowerCase()
                            .replace(".", "")
                            .replace("!", "");

                    if (response.equals("blody red, brother")) {
                        out.println("Welcome to the home, brother " + name + "!");
                    } else {
                        out.println("Go away, you will nothing find here!");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }