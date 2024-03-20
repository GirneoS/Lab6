package org.example.server;

import org.example.models.ExecutableCommand;
import org.example.controller.Serialization;

import java.io.IOException;
import java.nio.channels.*;
import java.util.logging.Logger;

public class InputServer {
    private static final Logger logger = Logger.getLogger("StartServerLogger");
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        logger.info("The server has just start!\n-------------------------------------------------\n");


        while(true) {
            ExecutableCommand executableCommand = ServerNetController.GetRequest();

            var result = executableCommand.execute();
            System.out.println(result);

            byte[] bytesOfResult = Serialization.SerializeObject(result);
            ServerNetController.SendResponse(bytesOfResult);
        }
    }
}
