package uk.co.birchlabs.license_bridge.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BridgeServerAPI extends Remote {
    String getMessage() throws RemoteException;
}
