package org.example;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("127.0.0.1", 8989)) {
            try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()))) {
                    out.println("{\"title\": \"булка\", \"date\": \"2022.02.08\", \"sum\": 200}");

                   System.out.println(in.readLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
