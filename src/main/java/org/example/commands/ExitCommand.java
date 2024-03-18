package org.example.commands;

import com.sun.tools.javac.Main;
import org.example.ExecutableCommand;
import org.example.MainCollection;

import java.io.*;
import java.util.Arrays;

public class ExitCommand implements ExecutableCommand, Serializable {
    private String[] cmd;

    /**
     * This method contains logic for "exit" command. Here the program exits from app.
     * @param command command with arguments from the console
     */
    @Override
    public void execute() {
        System.out.println("\033[0;34m" + "Выход из приложения..." + "\u001B[0m");

        try(FileOutputStream writer = new FileOutputStream("SavedApp");
            ObjectOutputStream oos = new ObjectOutputStream(writer)){

            MainCollection.getQueue().forEach(x -> {
                try {
                    oos.writeObject(x);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            System.exit(0);
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    /**
     * This method validates an arguments for "exit" command
     * @param command command with arguments from the console
     * @return returns true if arguments was entered correctly and false if it was entered incorrectly
     */
    @Override
    public boolean validate() {
        if(cmd.length==1){
            return true;
        }else{
            System.out.println("\u001B[31m" + "У команды exit нет аргументов!" + "\u001B[0m");
            return false;
        }
    }

    @Override
    public void setCmd(String[] cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return "ExitCommand{" +
                "cmd=" + Arrays.toString(cmd) +
                '}';
    }
}
