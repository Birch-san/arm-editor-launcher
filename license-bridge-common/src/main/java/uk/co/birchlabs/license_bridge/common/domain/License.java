package uk.co.birchlabs.license_bridge.common.domain;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface License extends Remote {
    int status() throws RemoteException;

    void checkin() throws RemoteException;

    int getAttrHealth() throws RemoteException;

    String getActKey() throws RemoteException;

    boolean goodOnce() throws RemoteException;

    int getCLientCache() throws RemoteException;

    String getContract() throws RemoteException;

    int getDetachedDemo() throws RemoteException;

    String getCustomer() throws RemoteException;

    int getCount() throws RemoteException;

    String getExpiration() throws RemoteException;

    int getHold() throws RemoteException;

    int hostBased() throws RemoteException;

    String getHostID() throws RemoteException;

    String getIssued() throws RemoteException;

    String getIssuer() throws RemoteException;

    boolean isCached() throws RemoteException;

    String getLineItem() throws RemoteException;

    int getMaxRoam() throws RemoteException;

    int getMaxRoamCount() throws RemoteException;

    int getMinRemove() throws RemoteException;

    int getMinTimeout() throws RemoteException;

    String getPlatforms() throws RemoteException;

    String getProduct() throws RemoteException;

    String getServer() throws RemoteException;

    int getShare() throws RemoteException;

    int getSoftLimit() throws RemoteException;

    String getStart() throws RemoteException;

    int getType() throws RemoteException;

    int getTimezone() throws RemoteException;

    int userBased() throws RemoteException;

    String getVersion() throws RemoteException;

    String getLFPath() throws RemoteException;

    int daysToExpiration() throws RemoteException;

    boolean isRoaming() throws RemoteException;

    int getMaxShare() throws RemoteException;

    int getNamedUserCount() throws RemoteException;

    int getNamedUserMinHours() throws RemoteException;

    String getOptions() throws RemoteException;

    int getMinCheckout() throws RemoteException;

    boolean authCheck(String license) throws RemoteException;

    boolean isSingle() throws RemoteException;

    boolean isUncounted() throws RemoteException;

    long getLicenseHandle() throws RemoteException;
}
