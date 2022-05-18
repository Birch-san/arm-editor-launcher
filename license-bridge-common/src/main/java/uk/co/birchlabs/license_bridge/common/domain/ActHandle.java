package uk.co.birchlabs.license_bridge.common.domain;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ActHandle extends Remote {
    void setExtra(String extra) throws RemoteException;

    void setHostidList(String hostidList) throws RemoteException;

    void setHostname(String hostname) throws RemoteException;

    void setLogString(String logString) throws RemoteException;

    void setISV(String ISV) throws RemoteException;

    void setRehostable(boolean rehostable) throws RemoteException;
}
