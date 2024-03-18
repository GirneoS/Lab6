package org.example.client;

import org.example.Execution;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        String[] command;
        while(true){
            System.out.print(">");
            if(scanner.hasNextLine()) {
                command = scanner.nextLine().split(" ");
                Execution.executeCommand(command);
            }else{
                command = new String[]{"exit"};
                Execution.executeCommand(command);
                break;
            }
        }
    }
}
