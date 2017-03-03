package com.company;

import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

/**
 * Created by Aleson on 1/18/2017.
 */
public class ClientThread extends Thread {

    Registry registry;
    Socket clientSocket;

    public ClientThread(Registry registry, Socket clientSocket) {
        this.registry = registry;
        this.clientSocket = clientSocket;
        this.start();
        System.out.println("Request received from Client on");
        long threadId = Thread.currentThread().getId();
        System.out.println("Thread id : "+threadId);
    }

    @Override
    public void run() {
        super.run();
        try {
            Inventry inventry = new Inventry();
            registry.rebind("Inventry",inventry);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        finally {
            try {
                clientSocket.close();
                System.out.println("Client Exiting");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
