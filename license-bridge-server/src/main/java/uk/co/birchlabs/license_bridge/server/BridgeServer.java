package uk.co.birchlabs.license_bridge.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;

import uk.co.birchlabs.license_bridge.common.BridgeServerAPI;

public class BridgeServer extends UnicastRemoteObject implements BridgeServerAPI {
    public static final String MESSAGE = "Hello World";

    public BridgeServer() throws RemoteException {
        super(0); // required to avoid the 'rmic' step, see below
    }

    public String getMessage() {
        return MESSAGE;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("RMI server started");

        try { //special exception handler for registry creation
            LocateRegistry.createRegistry(1099);
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }

        //Instantiate RmiServer
        BridgeServer server = new BridgeServer();

        // Bind this object instance to the name "BridgeServer"
        Naming.rebind("//localhost/BridgeServer", server);
        System.out.println("PeerServer bound in registry");
    }
}