package uk.co.birchlabs.license_bridge.common.domain;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AvailableProduct extends Remote {
    String getName() throws RemoteException;

    String getVersion() throws RemoteException;

    String getActKey() throws RemoteException;

    int getCount() throws RemoteException;

    String getContract() throws RemoteException;

    String getCustomer() throws RemoteException;

    String getExpiration() throws RemoteException;

    int getExpDays() throws RemoteException;

    int getCurrentInUse() throws RemoteException;

    int getCurrentResUse() throws RemoteException;

    int getHostBased() throws RemoteException;

    String getHostId() throws RemoteException;

    int getHold() throws RemoteException;

    String getIssuer() throws RemoteException;

    int getMaxRoam() throws RemoteException;

    int getMaxRoamCount() throws RemoteException;

    int getMaxShare() throws RemoteException;

    int getMinCheckout() throws RemoteException;

    int getMinTimeout() throws RemoteException;

    int getMinRemove() throws RemoteException;

    int getNRes() throws RemoteException;

    int getNRoamAllowed() throws RemoteException;

    int getCurrentRoam() throws RemoteException;

    String getOptions() throws RemoteException;

    String getServer() throws RemoteException;

    int getShare() throws RemoteException;

    int getSoftLimit() throws RemoteException;

    int getTimeout() throws RemoteException;

    int getTimezone() throws RemoteException;

    boolean getIsRoaming() throws RemoteException;

    boolean getIsTokenBased() throws RemoteException;

    int getType() throws RemoteException;

    int getUserBased() throws RemoteException;
}
