package com.reprisesoftware.rlm;

import uk.co.birchlabs.license_bridge.client.BridgeClient;
import uk.co.birchlabs.license_bridge.client.RuntimeRemoteException;
import uk.co.birchlabs.license_bridge.common.domain.AvailableProduct;

import java.rmi.RemoteException;

public class RlmAvailableProduct implements RlmConstants {
    public final AvailableProduct peer;
    RlmAvailableProduct() {
        try {
            this.peer = BridgeClient.Singleton.getAPI().AvailableProduct();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getName() {
        try {
            return this.peer.getName();
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

    public String getActKey() {
        try {
            return this.peer.getActKey();
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

    public String getContract() {
        try {
            return this.peer.getContract();
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

    public String getExpiration() {
        try {
            return this.peer.getExpiration();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getExpDays() {
        try {
            return this.peer.getExpDays();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getCurrentInUse() {
        try {
            return this.peer.getCurrentInUse();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getCurrentResUse() {
        try {
            return this.peer.getCurrentResUse();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getHostBased() {
        try {
            return this.peer.getHostBased();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getHostId() {
        try {
            return this.peer.getHostId();
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

    public String getIssuer() {
        try {
            return this.peer.getIssuer();
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

    public int getMaxShare() {
        try {
            return this.peer.getMaxShare();
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

    public int getMinTimeout() {
        try {
            return this.peer.getMinTimeout();
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

    public int getNRes() {
        try {
            return this.peer.getNRes();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getNRoamAllowed() {
        try {
            return this.peer.getNRoamAllowed();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getCurrentRoam() {
        try {
            return this.peer.getCurrentRoam();
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

    public int getTimeout() {
        try {
            return this.peer.getTimeout();
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

    public boolean getIsRoaming() {
        try {
            return this.peer.getIsRoaming();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean getIsTokenBased() {
        try {
            return this.peer.getIsTokenBased();
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

    public int getUserBased() {
        try {
            return this.peer.getUserBased();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
