package org.example.commands;

import org.example.ExecutableCommand;
import org.example.MainCollection;
import org.example.basics.Dragon;

import java.io.Serializable;
import java.util.Arrays;

public class InfoCommand implements ExecutableCommand, Serializable {
    private String[] cmd;

    /**
     * This method contains the logic for "info" command. Here the program prints type of main collection in app, date and time of initialization and number of dragons in collection.
     * @param command command with arguments from the console
     */
    @Override
    public void execute() {
            System.out.println("Тип: PriorityQueue\nДата инициализации: " + MainCollection.getInitDate() + "\nКоличество элементов: " + MainCollection.getQueue().size());
            HistoryCommand.UpdateHistory("info");
    }

    /**
     * This method validates an arguments for "info" command.
     * @param command command with arguments from the console
     * @return returns true if user not entered arguments and false if he entered some.
     */
    @Override
    public boolean validate() {
        if (cmd.length == 1) {
            return true;
        } else {
            System.out.println("\u001B[31m" + "У команды info нет аргументов!" + "\u001B[0m");
            return false;
        }
    }

    @Override
    public void setCmd(String[] cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return "InfoCommand{" +
                "cmd=" + Arrays.toString(cmd) +
                '}';
    }
}
