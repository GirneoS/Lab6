package org.example;

import java.io.*;

public class Serialization {
    public static <T> byte[] serializeObject(T obj) {
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(baos)){

            oos.writeObject(obj);
            return baos.toByteArray();

        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T deserializeObject(byte[] buffer) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(buffer); ObjectInputStream ois = new ObjectInputStream(bais)) {

            return (T) ois.readObject();

        }catch(EOFException e){
            e.printStackTrace();
        }catch(IOException | ClassNotFoundException e){

            System.out.println("Ошибка при дессиреализации объекта");
            e.printStackTrace();

        }
        return null;
    }
}
