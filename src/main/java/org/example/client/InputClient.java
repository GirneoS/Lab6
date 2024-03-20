package org.example.client;

import java.io.IOException;
import java.util.*;

public class InputClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        String[] command;
        while(true){
            System.out.print(">");
            if(scanner.hasNextLine()) {
                command = scanner.nextLine().split(" ");
                ClientCommandController.parseCommand(command);
            }else{
                command = new String[]{"exit"};
                ClientCommandController.parseCommand(command);
                break;
            }
        }

    }
}
