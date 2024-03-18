package org.example.commands;

import org.example.ExecutableCommand;
import org.example.MainCollection;
import org.example.basics.Dragon;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

public class FilterStartsWithNameCommand implements ExecutableCommand, Serializable {
    private String[] cmd;
    @Override
    public void execute() {
            String subName = cmd[1];

            Optional<Dragon> dragon = MainCollection.getQueue().stream()
                    .filter(x -> x.getName().startsWith(subName))
                    .findFirst();

            dragon.ifPresentOrElse(
                    v -> {HistoryCommand.UpdateHistory("filter_starts_with_name");
                        System.out.println(dragon);},
                    () -> System.out.println("\u001B[31m" + "В коллекции нет драконов, у которых имя начинается с указанной подстроки!" + "\u001B[0m")
            );
    }

    @Override
    public boolean validate() {
        if(cmd.length==2)
            return true;
        else {
            System.out.println("\u001B[31m" + "У команды filter_starts_with_name должен быть 1 аргумент!" + "\u001B[0m");
            return false;
        }
    }

    @Override
    public void setCmd(String[] cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return "FilterStartsWithNameCommand{" +
                "cmd=" + Arrays.toString(cmd) +
                '}';
    }
}
