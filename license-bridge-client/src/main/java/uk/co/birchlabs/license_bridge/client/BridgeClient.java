package uk.co.birchlabs.license_bridge.client;

import uk.co.birchlabs.license_bridge.common.BridgeServerAPI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public enum BridgeClient {
    Singleton;
    public final BridgeServerAPI api;
    BridgeClient() {
        try {
            api = (BridgeServerAPI) Naming.lookup("//localhost/BridgeServer");
        } catch (MalformedURLException | NotBoundException | RemoteException ex) {
            throw new RuntimeException(ex);
        }
    }
}
