package uk.co.birchlabs.license_bridge.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class BridgeServerDeploy {
    public static void deploy(String serverName, int registryPort) throws Exception {
        System.out.println("RMI server started");

        try { //special exception handler for registry creation
            LocateRegistry.createRegistry(registryPort);
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }

        //Instantiate RmiServer
        final BridgeServer server = new BridgeServer();

        // Bind this object instance to the name "BridgeServer"
        Naming.rebind(serverName, server);
        System.out.println("PeerServer bound in registry");
    }
}
