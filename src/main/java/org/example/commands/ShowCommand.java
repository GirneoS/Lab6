package org.example.commands;

import org.example.ExecutableCommand;
import org.example.MainCollection;
import org.example.basics.Dragon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ShowCommand implements ExecutableCommand, Serializable {
    private String[] cmd;

    /**
     * This method contains the logic for "show" command. Here the program saves the collection in file "SavedCollection".
     * @param command command with arguments from the console.
     */
    @Override
    public String execute() {
        List<String> result = MainCollection.getQueue().stream()
                        .map(Dragon::toString)
                        .collect(Collectors.toList());

        System.out.println(String.join("\n",result));
        HistoryCommand.UpdateHistory("show");
        return String.join("\n",result);
    }

    /**
     * This method validates an arguments for "show" command.
     * @param command command with arguments from the console
     * @return returns true if user not entered arguments and false if he entered some.
     */
    @Override
    public boolean validate() {
        if(cmd.length==1){
            return true;
        }else{
            System.out.println("\u001B[31m" + "У команды \"show\" нет аргументов!" + "\u001B[0m");
            return false;
        }

    }

    @Override
    public void setCmd(String[] cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return "ShowCommand{" +
                "cmd=" + Arrays.toString(cmd) +
                '}';
    }
}
