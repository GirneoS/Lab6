package org.example.server;

import org.example.controller.ExecutableCommand;
import org.example.controller.Serialization;

import java.io.*;
import java.net.*;
import java.util.logging.Logger;

public class ServerNetController {

    private static final Logger logger = Logger.getLogger("Laba6");
    private static boolean connection = false;
    public static void SendResponse(byte[] arr) throws SocketException {
        try(DatagramSocket socket = new DatagramSocket()){

            SocketAddress address = new InetSocketAddress("localhost",8186);
            DatagramPacket packet = new DatagramPacket(arr, arr.length, address);

            socket.send(packet);
            logger.info("Response just has been sent\n-------------------------------------------------\n\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ExecutableCommand GetRequest() throws IOException, ClassNotFoundException {

        try(DatagramSocket socket = new DatagramSocket(8187)){
            byte[] bytesOfRequest = new byte[2048];

            DatagramPacket requestPacket = new DatagramPacket(bytesOfRequest, bytesOfRequest.length);
            socket.receive(requestPacket);

            if(!connection){
                logger.info("Client has connected to server\n-------------------------------------------------");
                connection = true;

            }

            ExecutableCommand command = Serialization.DeserializeObject(bytesOfRequest);

            logger.info("Request has been received - "+command.getClass()+"\n-------------------------------------------------");

            return command;
        }

    }

}
