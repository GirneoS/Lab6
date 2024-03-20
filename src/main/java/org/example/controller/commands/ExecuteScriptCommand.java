package org.example.controller.commands;

import org.example.controller.ExecutableCommand;
import org.example.server.ServerCommandController;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ExecuteScriptCommand implements ExecutableCommand, Serializable {
    ArrayList<String> fileNames = new ArrayList<>();
    private String[] cmd;

    /**
     * This method contains logic for "execute_script" command. Here the program reading commands from file.
     */
    @Override
    public String execute() {
        ArrayList<String> executedCommandResult = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(cmd[1]))){
            var line = reader.readLine();
            while(line!=null){
                if(line.split(" ")[0].equals("execute_script")){
                    if(fileNames.contains(line.split(" ")[1])){
                        return "\u001B[31m" + "В файле не может быть команды \"execute_script\", которая вызывает изначальный файл, т. к. это приведет к рекурсии!" + "\u001B[0m";
                    }
                    fileNames.add(line.split(" ")[1]);
                }
                ServerCommandController.parseCommand(line.split(" "));
                line = reader.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        HistoryCommand.UpdateHistory("execute_script");
        return null;
    }

    /**
     * This method validates an arguments for "execute_script" command
     * @return returns true if arguments was entered correctly and false if it was entered incorrectly
     */
    @Override
    public boolean validate() {
        if(cmd.length==2){
            if(cmd[1].startsWith(" ")){
                System.out.println("\u001B[31m" + "Имя файла не может начинаться или заканчиваться с пробела!" + "\u001B[0m");
                return false;
            }
            if(cmd[1].startsWith(".")){
                System.out.println("\u001B[31m" + "Имя файла не может начинаться или заканчиваться с точки!" + "\u001B[0m");
                return false;
            }
            return true;
        }else{
            System.out.println("\u001B[31m" + "У команды execute_script должен быть 1 аргумент(название файла должно быть в одно слово)!" + "\u001B[0m");
            return false;
        }
    }

    public void setCmd(String[] cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return "ExecuteScriptCommand{" +
                "cmd=" + Arrays.toString(cmd) +
                '}';
    }
}
