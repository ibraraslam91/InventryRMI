package com.company;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;




public class Main {

    static Registry registry;

    public static void main(String[] args) {

        try{
            int serverPort = 8888;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            registry = LocateRegistry.createRegistry(1099);

            while(true) {
                Socket clientSocket = listenSocket.accept();
                ClientThread clientThread = new ClientThread(registry,clientSocket);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
