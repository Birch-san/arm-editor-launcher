package uk.co.birchlabs.license_bridge.server.domain;

import com.reprisesoftware.rlm.RlmActInfo;
import uk.co.birchlabs.license_bridge.common.domain.ActInfo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ActInfoImpl extends UnicastRemoteObject implements ActInfo {
    private transient final RlmActInfo delegate;

    public ActInfoImpl(RlmActInfo delegate) throws RemoteException {
        super();
        this.delegate = delegate;
    }

    @Override
    public String getActKey() {
        return this.delegate.getActKey();
    }

    @Override
    public String getURL() {
        return this.delegate.getURL();
    }

    @Override
    public String getProduct() {
        return this.delegate.getProduct();
    }

    @Override
    public String getVersion() {
        return this.delegate.getVersion();
    }

    @Override
    public String getUpgradeVersion() {
        return this.delegate.getUpgradeVersion();
    }

    @Override
    public int getDateBased() {
        return this.delegate.getDateBased();
    }

    @Override
    public int getLicenseType() {
        return this.delegate.getLicenseType();
    }

    @Override
    public int getCount() {
        return this.delegate.getCount();
    }

    @Override
    public int getFulfilled() {
        return this.delegate.getFulfilled();
    }

    @Override
    public int getRehosts() {
        return this.delegate.getRehosts();
    }

    @Override
    public int getRevoked() {
        return this.delegate.getRevoked();
    }

    @Override
    public String getExpiration() {
        return this.delegate.getExpiration();
    }

    @Override
    public String getHostid() {
        return this.delegate.getHostid();
    }
}
