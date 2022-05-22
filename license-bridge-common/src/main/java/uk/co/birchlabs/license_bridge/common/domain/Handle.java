package uk.co.birchlabs.license_bridge.common.domain;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface Handle extends Remote {
    void loadLib(String libName) throws RemoteException;

    void close() throws RemoteException;

    /** @deprecated */
    String getHostID(int type) throws RemoteException;

    String[] getAllHostIDs(int type) throws RemoteException;

    void writeReportLog(String logString) throws RemoteException;

    void writeDebugLog(String logString) throws RemoteException;

    void createDetachedDemo(int days, String license) throws RemoteException;

    void removeDetachedDemo(String product, String version) throws RemoteException;

    Vector<AvailableProduct> getAvailableProducts(String product, String version) throws RemoteException;

    void setUser(String user) throws RemoteException;

    void setHost(String host) throws RemoteException;

    void setIsvData(String isvData) throws RemoteException;

    void enableLogging(int enable) throws RemoteException;

    void setPassword(String password) throws RemoteException;

    void setOption(String option) throws RemoteException;

    void skipIsvDown() throws RemoteException;

    void forgetIsvDown() throws RemoteException;

    void keepConn() throws RemoteException;

    String actRequest(String url, String isv, String key, String hostidList, String hostName, int count, String extra) throws RemoteException;

    String activateLicense(String url, String key, int count, ActHandle actHandle) throws RemoteException;

    void revokeLicense(String url, String product) throws RemoteException;

    void revokeLicenseReference(String url, String product) throws RemoteException;

    String disconnectedRevoke(String product) throws RemoteException;

    void processDisconnectedRevoke(String url, String verifyCode) throws RemoteException;

    boolean actKeyValid(String url, String actKey, String hostID) throws RemoteException;

    String actKeyValidLicense(String url, String actKey, String hostID) throws RemoteException;

    boolean previouslyActivated() throws RemoteException;

    String getRehost(String product) throws RemoteException;

    void doClientSideDiagnostics(String diagFile) throws RemoteException;

    String getMessageByCode(int code) throws RemoteException;

    long getHandle() throws RemoteException;
}
