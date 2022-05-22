package com.reprisesoftware.rlm;

import uk.co.birchlabs.license_bridge.client.BridgeClient;
import uk.co.birchlabs.license_bridge.client.LicenseException;
import uk.co.birchlabs.license_bridge.client.RuntimeRemoteException;
import uk.co.birchlabs.license_bridge.common.domain.ActInfo;

import java.rmi.RemoteException;

public class RlmActInfo implements RlmConstants {
    public final ActInfo peer;

    public RlmActInfo(RlmHandle rh, String url, String actKey) throws RlmException {
        try {
            this.peer = BridgeClient.Singleton.getAPI().ActInfo(rh.peer, url, actKey);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public String getActKey() {
        try {
            return this.peer.getActKey();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getURL() {
        try {
            return this.peer.getURL();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getProduct() {
        try {
            return this.peer.getProduct();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getVersion() {
        try {
            return this.peer.getVersion();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getUpgradeVersion() {
        try {
            return this.peer.getUpgradeVersion();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getDateBased() {
        try {
            return this.peer.getDateBased();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getLicenseType() {
        try {
            return this.peer.getLicenseType();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getCount() {
        try {
            return this.peer.getCount();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getFulfilled() {
        try {
            return this.peer.getFulfilled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getRehosts() {
        try {
            return this.peer.getRehosts();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getRevoked() {
        try {
            return this.peer.getRevoked();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getExpiration() {
        try {
            return this.peer.getExpiration();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getHostid() {
        try {
            return this.peer.getHostid();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
