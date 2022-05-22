package com.reprisesoftware.rlm;

import uk.co.birchlabs.license_bridge.client.BridgeClient;
import uk.co.birchlabs.license_bridge.client.LicenseException;
import uk.co.birchlabs.license_bridge.client.RuntimeRemoteException;
import uk.co.birchlabs.license_bridge.common.domain.Handle;

import java.rmi.RemoteException;
import java.util.Vector;

public class RlmHandle implements RlmConstants {
    public final Handle peer;
    public void loadLib(String libName) {
        try {
            this.peer.loadLib(libName);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public RlmHandle(String licLoc, String appPath, String license) throws RlmException {
        try {
            this.peer = BridgeClient.Singleton.getAPI().Handle(licLoc, appPath, license);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public RlmHandle(String licLoc, String appPath, String license, int promise) throws RlmException {
        try {
            this.peer = BridgeClient.Singleton.getAPI().Handle(licLoc, appPath, license, promise);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public RlmHandle(String licLoc, String appPath, String license, String[] env) throws RlmException {
        try {
            this.peer = BridgeClient.Singleton.getAPI().Handle(licLoc, appPath, license, env);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public RlmHandle(String licLoc, String appPath, String license, String libName) throws RlmException {
        try {
            this.peer = BridgeClient.Singleton.getAPI().Handle(licLoc, appPath, license, libName);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public RlmHandle(String licLoc, String appPath, String license, String libName, String[] env) throws RlmException {
        try {
            this.peer = BridgeClient.Singleton.getAPI().Handle(licLoc, appPath, license, libName, env);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public void close() {
        try {
            this.peer.close();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    /** @deprecated */
    public String getHostID(int type) {
        try {
            return this.peer.getHostID(type);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String[] getAllHostIDs(int type) {
        try {
            return this.peer.getAllHostIDs(type);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void writeReportLog(String logString) throws RlmException {
        try {
            this.peer.writeReportLog(logString);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public void writeDebugLog(String logString) throws RlmException {
        try {
            this.peer.writeDebugLog(logString);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public void createDetachedDemo(int days, String license) throws RlmException {
        try {
            this.peer.createDetachedDemo(days, license);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public void removeDetachedDemo(String product, String version) throws RlmException {
        try {
            this.peer.removeDetachedDemo(product, version);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public Vector getAvailableProducts(String product, String version) {
        try {
            return this.peer.getAvailableProducts(product, version);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setUser(String user) {
        try {
            this.peer.setUser(user);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setHost(String host) {
        try {
            this.peer.setHost(host);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setIsvData(String isvData) {
        try {
            this.peer.setIsvData(isvData);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void enableLogging(int enable) {
        try {
            this.peer.enableLogging(enable);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPassword(String password) {
        try {
            this.peer.setPassword(password);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setOption(String option) {
        try {
            this.peer.setOption(option);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void skipIsvDown() {
        try {
            this.peer.skipIsvDown();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void forgetIsvDown() {
        try {
            this.peer.forgetIsvDown();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void keepConn() {
        try {
            this.peer.keepConn();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String actRequest(String url, String isv, String key, String hostidList, String hostName, int count, String extra) throws RlmException {
        try {
            return this.peer.actRequest(url, isv, key, hostidList, hostName, count, extra);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public String activateLicense(String url, String key, int count, RlmActHandle actHandle) throws RlmException {
        try {
            return this.peer.activateLicense(url, key, count, actHandle.peer);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public void revokeLicense(String url, String product) throws RlmException {
        try {
            this.peer.revokeLicense(url, product);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public void revokeLicenseReference(String url, String product) throws RlmException {
        try {
            this.peer.revokeLicenseReference(url, product);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public String disconnectedRevoke(String product) throws RlmException {
        try {
            return this.peer.disconnectedRevoke(product);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public void processDisconnectedRevoke(String url, String verifyCode) throws RlmException {
        try {
            this.peer.processDisconnectedRevoke(url, verifyCode);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public boolean actKeyValid(String url, String actKey, String hostID) throws RlmException {
        try {
            return this.peer.actKeyValid(url, actKey, hostID);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public String actKeyValidLicense(String url, String actKey, String hostID) throws RlmException {
        try {
            return this.peer.actKeyValidLicense(url, actKey, hostID);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public boolean previouslyActivated() {
        try {
            return this.peer.previouslyActivated();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getRehost(String product) {
        try {
            return this.peer.getRehost(product);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void doClientSideDiagnostics(String diagFile) {
        try {
            this.peer.doClientSideDiagnostics(diagFile);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getMessageByCode(int code) {
        try {
            return this.peer.getMessageByCode(code);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public long getHandle() {
        try {
            return this.peer.getHandle();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
