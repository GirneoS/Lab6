package org.example.server;

import org.example.controller.ExecutableCommand;
import org.example.controller.Serialization;
import org.example.controller.commands.SaveCommand;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Logger;

public class InputServer {
    private static final Logger logger = Logger.getLogger("StartServerLogger");
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        logger.info("The server has just start!\n-------------------------------------------------\n");

        Scanner scanner = new Scanner(System.in);
        while(true) {
            if(System.in.available()>0) {
                String[] cmd = scanner.nextLine().split(" ");
                try{
                    if(cmd[0].equals("save")){
                        SaveCommand saveCommand = new SaveCommand();
                        saveCommand.setCmd(cmd);
                        if(saveCommand.validate()){
                            System.out.println(saveCommand.execute());
                        }
                    }else{
                        System.out.println("\u001B[31m" + "Введена неверная серверная команда!" + "\u001B[0m");
                    }
                }catch (NoSuchElementException e){
                    e.printStackTrace();
                }
                ExecutableCommand executableCommand = ServerNetController.GetRequest();

                var result = executableCommand.execute();
                System.out.println(result);

                byte[] bytesOfResult = Serialization.SerializeObject(result);
                ServerNetController.SendResponse(bytesOfResult);
            }
        }
    }
}
