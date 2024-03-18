package org.example.commands;

import org.example.ExecutableCommand;
import org.example.MainCollection;
import org.example.basics.Dragon;

import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.Collectors;

public class PrintDescendingCommand implements ExecutableCommand, Serializable {
    private String[] cmd;

    /**
     * This method adds "print_descending" command. Here the program prints all elements of main PriorityQueue in reverse order.
     * @param command command with arguments from the console.
     */
    @Override
    public void execute() {
            String reverseOrder = "";

            for (Dragon d : MainCollection.getQueue()) {
                reverseOrder = d +"\n"+ reverseOrder;
            }
            System.out.print(reverseOrder);
            HistoryCommand.UpdateHistory("print_descending");
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
