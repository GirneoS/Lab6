package org.example.client;

import org.example.ExecutableCommand;
import org.example.Serialization;
import org.example.basics.Dragon;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

public class MessageServer {
    private static DatagramChannel channel;
    private static SocketAddress address;

    static{
        address = new InetSocketAddress("localhost", 8182);
    }
    public static void SendMessage(ExecutableCommand command){
        try {
            channel = DatagramChannel.open();

            ByteBuffer buff = ByteBuffer.wrap(Serialization.serializeObject(command));

            channel.send(buff, address);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void ReceiveMessage(){
        try(DatagramSocket socket = new DatagramSocket(new InetSocketAddress("localhost",8183))){
            byte[] info = new byte[256];

            DatagramPacket packet = new DatagramPacket(info, info.length);

            socket.receive(packet);


            var obj = Serialization.deserializeObject(info);

            System.out.println(obj instanceof Dragon);

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
