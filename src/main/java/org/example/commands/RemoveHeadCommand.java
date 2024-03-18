package org.example.commands;

import org.example.ExecutableCommand;
import org.example.MainCollection;
import org.example.basics.Dragon;

import java.io.Serializable;
import java.util.Arrays;

public class RemoveHeadCommand implements ExecutableCommand, Serializable {
    private String[] cmd;
    /**
     * This method contains the logic for "remove_head" command. Here the program prints first element in the PriorityQueue and delete it.
     * @param command command with arguments from the console.
     */
    @Override
    public void execute() {
            if(MainCollection.getQueue().isEmpty()){
                System.out.println("\u001B[31m" + "Нельзя выполнить \"remove_head\", т. к. коллекция пустая!" + "\u001B[0m");
            }else {
                System.out.println(MainCollection.getQueue().poll());
                HistoryCommand.UpdateHistory("remove_head");
            }
    }
    /**
     * This method validates an arguments for "remove_head" command.
     * @param command command with arguments from the console
     * @return returns true if user not entered arguments and false if he entered some.
     */
    @Override
    public boolean validate() {
        if(cmd.length==1){
            return true;
        }else{
            System.out.println("\u001B[31m" + "У команды нет аргументов!" + "\u001B[0m");
            return false;
        }
    }

    @Override
    public void setCmd(String[] cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return "RemoveHeadCommand{" +
                "cmd=" + Arrays.toString(cmd) +
                '}';
    }
}
