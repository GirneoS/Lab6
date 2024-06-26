package org.example.controller.commands;

import org.example.controller.ExecutableCommand;
import org.example.models.MainCollection;
import org.example.models.basics.Dragon;

import java.io.Serializable;
import java.util.Arrays;

public class RemoveByIdCommand implements ExecutableCommand, Serializable {
    private static final long serialVersionUID = 11L;
    private String[] cmd;

    /**
     * This method adds "remove_by_id" command. Here the program removes an element from main PriorityQueue by its id.
     * @param command command with arguments from the console.
     */
    @Override
    public String execute() {

            int ID = Integer.parseInt(cmd[1]);
            Dragon dragon = MainCollection.getQueue().stream()
                    .filter(x -> x.getId() == ID)
                    .findFirst().orElse(null);

            if(dragon == null){
                return "\u001B[31m" + "Дракона с таким id не существует в коллекции!" + "\u001B[0m";
            }else{
                MainCollection.getQueue().remove(dragon);
                HistoryCommand.UpdateHistory("remove_by_id");
                return "Вы удалили дракона с id = " + dragon.getId();
            }
    }

    /**
     * This method validates an arguments for "remove_by_id" command
     * @param command command with arguments from the console
     * @return returns true if arguments was entered correctly and false if it was entered incorrectly
     */
    @Override
    public boolean validate() {
        if(cmd.length==2){
            try{
                Integer.parseInt(cmd[1]);
                return true;
            }catch(NumberFormatException e){
                System.out.println("\u001B[31m" + "id должен быть указан числом!" + "\u001B[0m");
                return false;
            }
        }else{
            System.out.println("\u001B[31m" + "В команде должен remove_by_id быть 1 аргумент!" + "\u001B[0m");
            return false;
        }
    }

    @Override
    public void setCmd(String[] cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return "RemoveByIdCommand{" +
                "cmd=" + Arrays.toString(cmd) +
                '}';
    }
}
