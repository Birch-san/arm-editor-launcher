package uk.co.birchlabs.license_bridge.client;

import uk.co.birchlabs.license_bridge.common.BridgeServerAPI;
import uk.co.birchlabs.license_bridge.common.BridgeServerConstants;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.function.Supplier;

public enum BridgeClient {
    Singleton;
    private BridgeServerAPI api;

    private BridgeServerAPI acquireAPI() {
        try {
            return (BridgeServerAPI) Naming.lookup(BridgeServerConstants.serverName);
        } catch (MalformedURLException | NotBoundException | RemoteException ex) {
            throw new RuntimeException(ex);
        }
    }

    public BridgeServerAPI getAPI() {
        if (api == null) {
            api = acquireAPI();
        }
        return api;
    }
}
