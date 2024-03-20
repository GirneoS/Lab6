package org.example.server;

import org.example.ExecutableCommand;
import org.example.Serialization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.NetworkChannel;
import java.util.Scanner;
import java.util.logging.Logger;

public class InputServer {
    private static Logger logger = Logger.getLogger("StartServerLogger");
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        logger.info("The server has just start!\n-------------------------------------------------\n");
//        Scanner scanner = new Scanner(System.in);
//        String[] command;


//        BufferedReader reader = new BufferedReader(new InputStreamReader(System));

        while(true) {
//            System.out.println(":");
//            if(scanner.hasNext()){
//                command = scanner.nextLine().split(" ");
//                ParseClientCommand.parseCommand(command);
//            }
//            ByteBuffer buffer = ByteBuffer.allocate(2048);

            ExecutableCommand executableCommand = ServerNet.GetRequest();

            var result = executableCommand.execute();
            System.out.println(result);

            byte[] bytesOfResult = Serialization.SerializeObject(result);
            ServerNet.SendResponse(bytesOfResult);
        }
    }
}
