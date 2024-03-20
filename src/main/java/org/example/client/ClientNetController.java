package org.example.client;

import org.example.controller.Serialization;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientNetController {
    public static void SendRequest(byte[] arr) throws IOException {

        try(DatagramChannel channel = DatagramChannel.open()){
            channel.configureBlocking(false);

            SocketAddress address = new InetSocketAddress("localhost", 8187);

            ByteBuffer buffer = ByteBuffer.wrap(arr);
            channel.send(buffer, address);
        }

    }

    public static void GetResponse() {

        try(DatagramSocket socket = new DatagramSocket(8186)){
            byte[] bytesOfResponse = new byte[2048];

            DatagramPacket response = new DatagramPacket(bytesOfResponse, bytesOfResponse.length);
            socket.receive(response);

            String text_response = Serialization.DeserializeObject(bytesOfResponse);
            System.out.println(text_response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
