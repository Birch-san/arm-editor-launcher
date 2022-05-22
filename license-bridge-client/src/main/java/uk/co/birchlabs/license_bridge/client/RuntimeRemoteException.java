package uk.co.birchlabs.license_bridge.client;

import java.rmi.RemoteException;

public class RuntimeRemoteException extends RuntimeException {
    public RuntimeRemoteException(RemoteException cause) {
        super(cause);
    }
}
