package uk.co.birchlabs.license_bridge.server.domain;

import com.reprisesoftware.rlm.RlmAvailableProduct;
import uk.co.birchlabs.license_bridge.common.domain.AvailableProduct;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AvailableProductImpl extends UnicastRemoteObject implements AvailableProduct {
    private transient final RlmAvailableProduct delegate;
    public AvailableProductImpl(RlmAvailableProduct delegate) throws RemoteException {
        super();
        this.delegate = delegate;
    }

    @Override
    public String getName() {
        return this.delegate.getName();
    }

    @Override
    public String getVersion() {
        return this.delegate.getVersion();
    }

    @Override
    public String getActKey() {
        return this.delegate.getActKey();
    }

    @Override
    public int getCount() {
        return this.delegate.getCount();
    }

    @Override
    public String getContract() {
        return this.delegate.getContract();
    }

    @Override
    public String getCustomer() {
        return this.delegate.getCustomer();
    }

    @Override
    public String getExpiration() {
        return this.delegate.getExpiration();
    }

    @Override
    public int getExpDays() {
        return this.delegate.getExpDays();
    }

    @Override
    public int getCurrentInUse() {
        return this.delegate.getCurrentInUse();
    }

    @Override
    public int getCurrentResUse() {
        return this.delegate.getCurrentResUse();
    }

    @Override
    public int getHostBased() {
        return this.delegate.getHostBased();
    }

    @Override
    public String getHostId() {
        return this.delegate.getHostId();
    }

    @Override
    public int getHold() {
        return this.delegate.getHold();
    }

    @Override
    public String getIssuer() {
        return this.delegate.getIssuer();
    }

    @Override
    public int getMaxRoam() {
        return this.delegate.getMaxRoam();
    }

    @Override
    public int getMaxRoamCount() {
        return this.delegate.getMaxRoamCount();
    }

    @Override
    public int getMaxShare() {
        return this.delegate.getMaxShare();
    }

    @Override
    public int getMinCheckout() {
        return this.delegate.getMinCheckout();
    }

    @Override
    public int getMinTimeout() {
        return this.delegate.getMinTimeout();
    }

    @Override
    public int getMinRemove() {
        return this.delegate.getMinRemove();
    }

    @Override
    public int getNRes() {
        return this.delegate.getNRes();
    }

    @Override
    public int getNRoamAllowed() {
        return this.delegate.getNRoamAllowed();
    }

    @Override
    public int getCurrentRoam() {
        return this.delegate.getCurrentRoam();
    }

    @Override
    public String getOptions() {
        return this.delegate.getOptions();
    }

    @Override
    public String getServer() {
        return this.delegate.getServer();
    }

    @Override
    public int getShare() {
        return this.delegate.getShare();
    }

    @Override
    public int getSoftLimit() {
        return this.delegate.getSoftLimit();
    }

    @Override
    public int getTimeout() {
        return this.delegate.getTimeout();
    }

    @Override
    public int getTimezone() {
        return this.delegate.getTimezone();
    }

    @Override
    public boolean getIsRoaming() {
        return this.delegate.getIsRoaming();
    }

    @Override
    public boolean getIsTokenBased() {
        return this.delegate.getIsTokenBased();
    }

    @Override
    public int getType() {
        return this.delegate.getType();
    }

    @Override
    public int getUserBased() {
        return this.delegate.getUserBased();
    }
}
