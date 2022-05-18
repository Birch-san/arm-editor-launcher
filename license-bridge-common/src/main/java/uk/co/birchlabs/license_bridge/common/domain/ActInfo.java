package uk.co.birchlabs.license_bridge.common.domain;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ActInfo extends Remote {
    String getActKey() throws RemoteException;

    String getURL() throws RemoteException;

    String getProduct() throws RemoteException;

    String getVersion() throws RemoteException;

    String getUpgradeVersion() throws RemoteException;

    int getDateBased() throws RemoteException;

    int getLicenseType() throws RemoteException;

    int getCount() throws RemoteException;

    int getFulfilled() throws RemoteException;

    int getRehosts() throws RemoteException;

    int getRevoked() throws RemoteException;

    String getExpiration() throws RemoteException;

    String getHostid() throws RemoteException;
}
