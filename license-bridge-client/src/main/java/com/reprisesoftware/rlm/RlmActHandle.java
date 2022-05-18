package com.reprisesoftware.rlm;

import uk.co.birchlabs.license_bridge.client.BridgeClient;
import uk.co.birchlabs.license_bridge.client.LicenseException;
import uk.co.birchlabs.license_bridge.client.RuntimeRemoteException;
import uk.co.birchlabs.license_bridge.common.domain.ActHandle;

import java.rmi.RemoteException;

public class RlmActHandle implements RlmConstants {
    public final ActHandle peer;

    public RlmActHandle(RlmHandle rh) {
        try {
            this.peer = BridgeClient.Singleton.api.ActHandle(rh.peer);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setExtra(String extra) throws RlmException {
        try {
            this.peer.setExtra(extra);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public void setHostidList(String hostidList) throws RlmException {
        try {
            this.peer.setHostidList(hostidList);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public void setHostname(String hostname) throws RlmException {
        try {
            this.peer.setHostname(hostname);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public void setLogString(String logString) throws RlmException {
        try {
            this.peer.setLogString(logString);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public void setISV(String ISV) throws RlmException {
        try {
            this.peer.setISV(ISV);
        } catch (RemoteException e) {
            throw new LicenseException(e);
        }
    }

    public void setRehostable(boolean rehostable) {
        try {
            this.peer.setRehostable(rehostable);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}