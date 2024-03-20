package org.example.commands;

import org.example.ExecutableCommand;
import org.example.MainCollection;
import org.example.basics.Dragon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PrintDescendingCommand implements ExecutableCommand, Serializable {
    private String[] cmd;

    /**
     * This method adds "print_descending" command. Here the program prints all elements of main PriorityQueue in reverse order.
     * @param command command with arguments from the console.
     */
    @Override
    public String execute() {

        List<String> reverseDragons = MainCollection.getQueue().stream()
                    .map(Dragon::toString)
                    .toList();

        System.out.println(reverseDragons);
        HistoryCommand.UpdateHistory("print_descending");
        return String.join("\n", reverseDragons);
    }

    /**
     * This method validates an arguments for "print_descending" command.
     * @param command command with arguments from the console
     * @return returns true if user not entered arguments and false if he entered some.
     */
    @Override
    public boolean validate() {
        if(cmd.length==1)
            return true;
        else{
            System.out.println("\u001B[31m" + "У команды print_descending нет аргументов!" + "\u001B[0m");
            return false;
        }
    }

    @Override
    public void setCmd(String[] cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return "PrintDescendingCommand{" +
                "cmd=" + Arrays.toString(cmd) +
                '}';
    }
}
