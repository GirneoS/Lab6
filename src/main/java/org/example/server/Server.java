package org.example.server;

import org.example.ExecutableCommand;
import org.example.MainCollection;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class Server {
    private static final byte[] inf = new byte[1024];
    private static ByteBuffer buffer = ByteBuffer.wrap(inf);

    public static void main(String[] args) throws IOException {

        try(DatagramChannel channel = DatagramChannel.open()){

            channel.bind(new InetSocketAddress(8182));


            while(true) {
                channel.receive(buffer);

                ByteArrayInputStream bais = new ByteArrayInputStream(buffer.array());
                ObjectInputStream ois = new ObjectInputStream(bais);


                ExecutableCommand command = (ExecutableCommand) ois.readObject();

                System.out.println("\033[0;34m" + "Echo: "+command.toString() + "\u001B[0m \n");
                command.execute();
                System.out.println("\n \033[0;34m" + "------------------------------------------------------------------------------------" + "\u001B[0m");

                sendTextAnswer();

                buffer.clear();

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void sendTextAnswer(){
        try(DatagramSocket socket = new DatagramSocket()){

            byte[] answer = "Done".getBytes();
            InetAddress address = InetAddress.getLocalHost();
            int port = 8183;

            DatagramPacket packet = new DatagramPacket(answer, answer.length, address, port);
            socket.send(packet);

        } catch(IOException e){
            e.printStackTrace();
        }
    }

}
