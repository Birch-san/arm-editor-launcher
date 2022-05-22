package uk.co.birchlabs.license_bridge.server;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.reprisesoftware.rlm.*;
import uk.co.birchlabs.license_bridge.common.BridgeServerAPI;
import uk.co.birchlabs.license_bridge.common.BridgeServerConstants;
import uk.co.birchlabs.license_bridge.common.domain.*;
import uk.co.birchlabs.license_bridge.server.domain.*;
import uk.co.birchlabs.license_bridge.server.registry.ActHandleRegistry;
import uk.co.birchlabs.license_bridge.server.registry.AvailableProductRegistry;
import uk.co.birchlabs.license_bridge.server.registry.HandleRegistry;

public class BridgeServer extends UnicastRemoteObject implements BridgeServerAPI {
    private static final Constructor<RlmAvailableProduct> rAPConstructor;
    static {
        try {
            rAPConstructor = RlmAvailableProduct.class.getDeclaredConstructor();
            rAPConstructor.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    public BridgeServer() throws RemoteException {
        super(0);
    }

    @Override
    public String healthCheck() {
        return BridgeServerConstants.healthMessage;
    }

    @Override
    public Handle Handle(String licLoc, String appPath, String license) throws RemoteException {
        try {
            final RlmHandle rlmHandle = new RlmHandle(licLoc, appPath, license);
            return HandleRegistry.instance.dessicate(rlmHandle);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public Handle Handle(String licLoc, String appPath, String license, int promise) throws RemoteException {
        try {
            final RlmHandle rlmHandle = new RlmHandle(licLoc, appPath, license, promise);
            return HandleRegistry.instance.dessicate(rlmHandle);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public Handle Handle(String licLoc, String appPath, String license, String[] env) throws RemoteException {
        try {
            final RlmHandle rlmHandle = new RlmHandle(licLoc, appPath, license, env);
            return HandleRegistry.instance.dessicate(rlmHandle);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public Handle Handle(String licLoc, String appPath, String license, String libName) throws RemoteException {
        try {
            final RlmHandle rlmHandle = new RlmHandle(licLoc, appPath, license, libName);
            return HandleRegistry.instance.dessicate(rlmHandle);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public Handle Handle(String licLoc, String appPath, String license, String libName, String[] env) throws RemoteException {
        try {
            final RlmHandle rlmHandle = new RlmHandle(licLoc, appPath, license, libName, env);
            return HandleRegistry.instance.dessicate(rlmHandle);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public ActHandle ActHandle(Handle handle) throws RemoteException {
        final RlmHandle rh = HandleRegistry.instance.get(handle);

        final RlmActHandle rlmActHandle = new RlmActHandle(rh);
        return ActHandleRegistry.instance.dessicate(rlmActHandle);
    }

    @Override
    public ActInfo ActInfo(Handle handle, String url, String actKey) throws RemoteException {
        try {
            final RlmHandle rh = HandleRegistry.instance.get(handle);

            final RlmActInfo rlmActHandle = new RlmActInfo(rh, url, actKey);
            return new ActInfoImpl(rlmActHandle);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public AvailableProduct AvailableProduct() throws RemoteException {
        final RlmAvailableProduct rAP;
        try {
            rAP = rAPConstructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RemoteException(null, e);
        }
        return AvailableProductRegistry.instance.dessicate(rAP);
    }

    @Override
    public License License(Handle handle, String product, String version, int count) throws RemoteException {
        try {
            final RlmHandle rh = HandleRegistry.instance.get(handle);

            final RlmLicense rLicense = new RlmLicense(rh, product, version, count);
            return new LicenseImpl(rLicense);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public License License(Handle handle, AvailableProduct product, String version, int count) throws RemoteException {
        try {
            final RlmHandle rh = HandleRegistry.instance.get(handle);
            final RlmAvailableProduct rAP = AvailableProductRegistry.instance.get(product);

            final RlmLicense rLicense = new RlmLicense(rh, rAP, version, count);
            return new LicenseImpl(rLicense);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }
}