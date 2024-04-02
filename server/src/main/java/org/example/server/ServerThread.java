package org.example.server;

import java.io.IOException;
import java.util.Scanner;

public class ServerThread extends Thread{
    @Override
    public void run() {
        while(true) {
            Scanner scanner = new Scanner(System.in);

            String[] command = scanner.nextLine().split(" ");

            try {
                ServerCommandController.executeCommand(command);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
