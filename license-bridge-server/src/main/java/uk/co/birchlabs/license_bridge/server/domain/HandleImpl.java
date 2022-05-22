package uk.co.birchlabs.license_bridge.server.domain;

import com.reprisesoftware.rlm.RlmActHandle;
import com.reprisesoftware.rlm.RlmAvailableProduct;
import com.reprisesoftware.rlm.RlmException;
import com.reprisesoftware.rlm.RlmHandle;
import uk.co.birchlabs.license_bridge.common.domain.ActHandle;
import uk.co.birchlabs.license_bridge.common.domain.AvailableProduct;
import uk.co.birchlabs.license_bridge.common.domain.Handle;
import uk.co.birchlabs.license_bridge.server.registry.ActHandleRegistry.HydrateActHandle;
import uk.co.birchlabs.license_bridge.server.registry.AvailableProductRegistry;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.util.stream.Collectors;

public class HandleImpl extends UnicastRemoteObject implements Handle {
    private static final Method getHandleHandle;
    static {
        try {
            getHandleHandle = RlmHandle.class.getDeclaredMethod("getHandle");
            getHandleHandle.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    private transient final RlmHandle delegate;
    private transient final HydrateActHandle hydrateActHandle;

    public HandleImpl(RlmHandle delegate, HydrateActHandle hydrateActHandle) throws RemoteException {
        super();
        this.delegate = delegate;
        this.hydrateActHandle = hydrateActHandle;
    }

    @Override
    public void loadLib(String libName) {
        this.delegate.loadLib(libName);
    }

    @Override
    public void close() {
        this.delegate.close();
    }

    @Override
    public String getHostID(int type) {
        return this.delegate.getHostID(type);
    }

    @Override
    public String[] getAllHostIDs(int type) {
        return this.delegate.getAllHostIDs(type);
    }

    @Override
    public void writeReportLog(String logString) throws RemoteException {
        try {
            this.delegate.writeReportLog(logString);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public void writeDebugLog(String logString) throws RemoteException {
        try {
            this.delegate.writeDebugLog(logString);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public void createDetachedDemo(int days, String license) throws RemoteException {
        try {
            this.delegate.createDetachedDemo(days, license);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public void removeDetachedDemo(String product, String version) throws RemoteException {
        try {
            this.delegate.removeDetachedDemo(product, version);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public Vector<AvailableProduct> getAvailableProducts(String product, String version) throws RemoteException {
        final Vector<RlmAvailableProduct> vec = (Vector<RlmAvailableProduct>) this.delegate.getAvailableProducts(product, version);
        return vec.stream()
                .sequential()
                .map(AvailableProductRegistry.instance::dessicateUnchecked)
                .collect(Collectors.toCollection(() -> new Vector<>(vec.size())));
    }

    @Override
    public void setUser(String user) {
        this.delegate.setUser(user);
    }

    @Override
    public void setHost(String host) {
        this.delegate.setHost(host);
    }

    @Override
    public void setIsvData(String isvData) {
        this.delegate.setIsvData(isvData);
    }

    @Override
    public void enableLogging(int enable) {
        this.delegate.enableLogging(enable);
    }

    @Override
    public void setPassword(String password) {
        this.delegate.setPassword(password);
    }

    @Override
    public void setOption(String option) {
        this.delegate.setOption(option);
    }

    @Override
    public void skipIsvDown() {
        this.delegate.skipIsvDown();
    }

    @Override
    public void forgetIsvDown() {
        this.delegate.forgetIsvDown();
    }

    @Override
    public void keepConn() {
        this.delegate.keepConn();
    }

    @Override
    public String actRequest(String url, String isv, String key, String hostidList, String hostName, int count, String extra) throws RemoteException {
        try {
            return this.delegate.actRequest(url, isv, key, hostidList, hostName, count, extra);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public String activateLicense(String url, String key, int count, ActHandle actHandle) throws RemoteException {
        final RlmActHandle rAH = hydrateActHandle.make(actHandle);
        try {
            return this.delegate.activateLicense(url, key, count, rAH);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public void revokeLicense(String url, String product) throws RemoteException {
        try {
            this.delegate.revokeLicense(url, product);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public void revokeLicenseReference(String url, String product) throws RemoteException {
        try {
            this.delegate.revokeLicenseReference(url, product);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public String disconnectedRevoke(String product) throws RemoteException {
        try {
            return this.delegate.disconnectedRevoke(product);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public void processDisconnectedRevoke(String url, String verifyCode) throws RemoteException {
        try {
            this.delegate.processDisconnectedRevoke(url, verifyCode);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public boolean actKeyValid(String url, String actKey, String hostID) throws RemoteException {
        try {
            return this.delegate.actKeyValid(url, actKey, hostID);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public String actKeyValidLicense(String url, String actKey, String hostID) throws RemoteException {
        try {
            return this.delegate.actKeyValidLicense(url, actKey, hostID);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public boolean previouslyActivated() throws RemoteException {
        return this.delegate.previouslyActivated();
    }

    @Override
    public String getRehost(String product) {
        return this.delegate.getRehost(product);
    }

    @Override
    public void doClientSideDiagnostics(String diagFile) {
        this.delegate.doClientSideDiagnostics(diagFile);
    }

    @Override
    public String getMessageByCode(int code) {
        return this.delegate.getMessageByCode(code);
    }

    @Override
    public long getHandle() throws RemoteException {
        try {
            final long value = (long) getHandleHandle.invoke(this.delegate);
            return value;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RemoteException(null, e);
        }
    }
}
