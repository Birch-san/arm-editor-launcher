package uk.co.birchlabs.license_bridge.server.domain;

import com.reprisesoftware.rlm.RlmException;
import com.reprisesoftware.rlm.RlmLicense;
import uk.co.birchlabs.license_bridge.common.domain.License;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LicenseImpl extends UnicastRemoteObject implements License {
    private static final Method getLicenseHandle;
    static {
        try {
            getLicenseHandle = RlmLicense.class.getDeclaredMethod("getLicenseHandle");
            getLicenseHandle.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private transient final RlmLicense delegate;

    public LicenseImpl(RlmLicense delegate) throws RemoteException {
        super();
        this.delegate = delegate;
    }

    @Override
    public int status() throws RemoteException {
        try {
            return this.delegate.status();
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public void checkin() {
        this.delegate.checkin();
    }

    @Override
    public int getAttrHealth() throws RemoteException {
        try {
            return this.delegate.getAttrHealth();
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public String getActKey() {
        return this.delegate.getActKey();
    }

    @Override
    public boolean goodOnce() {
        return this.delegate.goodOnce();
    }

    @Override
    public int getCLientCache() {
        return this.delegate.getCLientCache();
    }

    @Override
    public String getContract() {
        return this.delegate.getContract();
    }

    @Override
    public int getDetachedDemo() {
        return this.delegate.getDetachedDemo();
    }

    @Override
    public String getCustomer() {
        return this.delegate.getCustomer();
    }

    @Override
    public int getCount() {
        return this.delegate.getCount();
    }

    @Override
    public String getExpiration() {
        return this.delegate.getExpiration();
    }

    @Override
    public int getHold() {
        return this.delegate.getHold();
    }

    @Override
    public int hostBased() {
        return this.delegate.hostBased();
    }

    @Override
    public String getHostID() {
        return this.delegate.getHostID();
    }

    @Override
    public String getIssued() {
        return this.delegate.getIssued();
    }

    @Override
    public String getIssuer() {
        return this.delegate.getIssuer();
    }

    @Override
    public boolean isCached() {
        return this.delegate.isCached();
    }

    @Override
    public String getLineItem() {
        return this.delegate.getLineItem();
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
    public int getMinRemove() {
        return this.delegate.getMinRemove();
    }

    @Override
    public int getMinTimeout() {
        return this.delegate.getMinTimeout();
    }

    @Override
    public String getPlatforms() {
        return this.delegate.getPlatforms();
    }

    @Override
    public String getProduct() {
        return this.delegate.getProduct();
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
    public String getStart() {
        return this.delegate.getStart();
    }

    @Override
    public int getType() {
        return this.delegate.getType();
    }

    @Override
    public int getTimezone() {
        return this.delegate.getTimezone();
    }

    @Override
    public int userBased() {
        return this.delegate.userBased();
    }

    @Override
    public String getVersion() {
        return this.delegate.getVersion();
    }

    @Override
    public String getLFPath() {
        return this.delegate.getLFPath();
    }

    @Override
    public int daysToExpiration() {
        return this.delegate.daysToExpiration();
    }

    @Override
    public boolean isRoaming() {
        return this.delegate.isRoaming();
    }

    @Override
    public int getMaxShare() {
        return this.delegate.getMaxShare();
    }

    @Override
    public int getNamedUserCount() {
        return this.delegate.getNamedUserCount();
    }

    @Override
    public int getNamedUserMinHours() {
        return this.delegate.getNamedUserMinHours();
    }

    @Override
    public String getOptions() {
        return this.delegate.getOptions();
    }

    @Override
    public int getMinCheckout() {
        return this.delegate.getMinCheckout();
    }

    @Override
    public boolean authCheck(String license) throws RemoteException {
        try {
            return this.delegate.authCheck(license);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public boolean isSingle() {
        return this.delegate.isSingle();
    }

    @Override
    public boolean isUncounted() {
        return this.delegate.isUncounted();
    }

    @Override
    public long getLicenseHandle() throws RemoteException {
        try {
            final long value = (long) getLicenseHandle.invoke(this.delegate);
            return value;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RemoteException(null, e);
        }
    }
}
