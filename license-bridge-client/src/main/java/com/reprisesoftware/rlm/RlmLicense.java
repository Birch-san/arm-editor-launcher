package com.reprisesoftware.rlm;

import uk.co.birchlabs.license_bridge.client.BridgeClient;
import uk.co.birchlabs.license_bridge.client.LicenseException;
import uk.co.birchlabs.license_bridge.client.RuntimeRemoteException;
import uk.co.birchlabs.license_bridge.common.domain.License;

import java.rmi.RemoteException;

public class RlmLicense implements RlmConstants {
    public final License peer;
    public RlmLicense(RlmHandle handle, String product, String version, int count) throws RlmException {
        try {
            this.peer = BridgeClient.Singleton.api.License(handle.peer, product, version, count);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public RlmLicense(RlmHandle handle, RlmAvailableProduct product, String version, int count) throws RlmException {
        try {
            this.peer = BridgeClient.Singleton.api.License(handle.peer, product.peer, version, count);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public int status() throws RlmException {
        try {
            return this.peer.status();
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public void checkin() {
        try {
            this.peer.checkin();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getAttrHealth() throws RlmException {
        try {
            return this.peer.getAttrHealth();
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

    public boolean goodOnce() {
        try {
            return this.peer.goodOnce();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getCLientCache() {
        try {
            return this.peer.getCLientCache();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getContract() {
        try {
            return this.peer.getContract();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getDetachedDemo() {
        try {
            return this.peer.getDetachedDemo();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getCustomer() {
        try {
            return this.peer.getCustomer();
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

    public String getExpiration() {
        try {
            return this.peer.getExpiration();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getHold() {
        try {
            return this.peer.getHold();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int hostBased() {
        try {
            return this.peer.hostBased();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getHostID() {
        try {
            return this.peer.getHostID();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getIssued() {
        try {
            return this.peer.getIssued();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getIssuer() {
        try {
            return this.peer.getIssuer();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isCached() {
        try {
            return this.peer.isCached();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getLineItem() {
        try {
            return this.peer.getLineItem();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getMaxRoam() {
        try {
            return this.peer.getMaxRoam();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getMaxRoamCount() {
        try {
            return this.peer.getMaxRoamCount();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getMinRemove() {
        try {
            return this.peer.getMinRemove();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getMinTimeout() {
        try {
            return this.peer.getMinTimeout();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getPlatforms() {
        try {
            return this.peer.getPlatforms();
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

    public String getServer() {
        try {
            return this.peer.getServer();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getShare() {
        try {
            return this.peer.getShare();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getSoftLimit() {
        try {
            return this.peer.getSoftLimit();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getStart() {
        try {
            return this.peer.getStart();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getType() {
        try {
            return this.peer.getType();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getTimezone() {
        try {
            return this.peer.getTimezone();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int userBased() {
        try {
            return this.peer.userBased();
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

    public String getLFPath() {
        try {
            return this.peer.getLFPath();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int daysToExpiration() {
        try {
            return this.peer.daysToExpiration();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isRoaming() {
        try {
            return this.peer.isRoaming();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getMaxShare() {
        try {
            return this.peer.getMaxShare();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getNamedUserCount() {
        try {
            return this.peer.getNamedUserCount();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getNamedUserMinHours() {
        try {
            return this.peer.getNamedUserMinHours();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getOptions() {
        try {
            return this.peer.getOptions();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getMinCheckout() {
        try {
            return this.peer.getMinCheckout();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean authCheck(String license) throws RlmException {
        try {
            return this.peer.authCheck(license);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public boolean isSingle() {
        try {
            return this.peer.isSingle();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isUncounted() {
        try {
            return this.peer.isUncounted();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public long getLicenseHandle() {
        try {
            return this.peer.getLicenseHandle();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}

