package org.example.commands;

import org.example.ExecutableCommand;
import org.example.MainCollection;
import org.example.basics.Dragon;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

public class FilterByWingspanCommand implements ExecutableCommand, Serializable {
    private String[] cmd;

    /**
     * This method contains logic for "filter_by_wingspan" command. Here the program filtering elements from PriorityQueue and print them.
     * @param command command with arguments from the console
     */
    @Override
    public void execute() {
            float goal = Float.parseFloat(cmd[1]);
            Optional<Dragon> dragon = Optional.empty();

            try {
                dragon = MainCollection.getQueue().stream()
                        .filter(x -> x.getWingspan() == goal)
                        .findFirst();
            }catch(NullPointerException ignored){}


            Optional<Dragon> finalDragon = dragon;
            dragon.ifPresentOrElse(
                    v -> {HistoryCommand.UpdateHistory("filter_starts_with_name");
                        System.out.println(finalDragon);},
                    () -> System.out.println("\u001B[31m" + "В коллекции нет драконов, с указанным размахом крыльев!" + "\u001B[0m")

            );
    }

    /**
     * This method validates an arguments for "filter_by_wingspan" command
     * @param command command with arguments from the console
     * @return returns true if arguments was entered correctly and false if it was entered incorrectly
     */
    @Override
    public boolean validate() {
        if(cmd.length==2){
            try{
                Float.parseFloat(cmd[1]);
                return true;
            }catch(NumberFormatException e){
                System.out.println("\u001B[31m" + "Первый аргумент команды может быть задан только дробным числом!" + "\u001B[0m");
                return false;
            }
        }else {
            System.out.println("\u001B[31m" + "У команды filter_by_wingspan должен быть 1 аргумент!" + "\u001B[0m");
            return false;
        }
    }

    @Override
    public void setCmd(String[] cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return "FilterByWingspanCommand{" +
                "cmd=" + Arrays.toString(cmd) +
                '}';
    }
}
