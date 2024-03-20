package org.example.commands;

import org.example.ExecutableCommand;
import org.example.MainCollection;
import org.example.basics.Dragon;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;

public class SaveCommand implements ExecutableCommand, Serializable {
    private String[] cmd;

    /**
     * This method contains the logic for "save" command. Here the program saves the collection in file "SavedCollection".
     */
    @Override
    public String execute() {
            if (Files.isWritable(new File(System.getenv("SAVE_DRAGON")).toPath())){
                try (PrintWriter writer = new PrintWriter(new FileWriter(System.getenv("SAVE_DRAGON")))) {
                    writer.write("name,age,id,wingspan,DragonType"+"\n");

                    MainCollection.getQueue().forEach(d -> writer.write(d.getName()+","+d.getAge()+","+d.getId()+","+d.getWingspan()+","+d.getType() + "\n"));

                    HistoryCommand.UpdateHistory("save");
                    return "\033[0;34m" + "Коллекция сохранена в файл!" + "\u001B[0m";

                } catch (IOException e) {
                    e.printStackTrace();
                    return "Ошибка в блоке try";
                }
            }else{
                return "\u001B[31m" + "У вас нет прав доступа к файлу сохранения!" + "\u001B[0m";
            }
        }
    /**
     * This method validates an arguments for "save" command.
     * @param command command with arguments from the console
     * @return returns true if user not entered arguments and false if he entered some.
     */
    @Override
    public boolean validate() {
        if(cmd.length==1){
            return true;
        }else{
            System.out.println("\u001B[31m" + "У команды save не должно быть аргументов!" + "\u001B[0m");
            return false;
        }
    }

    @Override
    public void setCmd(String[] cmd) {
        this.cmd = cmd;
    }

    @Override
    public String toString() {
        return "SaveCommand{" +
                "cmd=" + Arrays.toString(cmd) +
                '}';
    }
}
