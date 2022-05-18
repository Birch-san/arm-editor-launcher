package uk.co.birchlabs.license_bridge.server;

import java.lang.reflect.Proxy;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ObjID;
import java.rmi.server.RemoteObjectInvocationHandler;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;
import java.util.HashMap;
import java.util.Map;

import com.reprisesoftware.rlm.*;
import sun.rmi.server.UnicastRef;
import sun.rmi.server.UnicastServerRef;
import uk.co.birchlabs.license_bridge.common.BridgeServerAPI;
import uk.co.birchlabs.license_bridge.common.domain.*;
import uk.co.birchlabs.license_bridge.server.domain.*;
import uk.co.birchlabs.license_bridge.server.domain.HandleImpl.HydrateActHandle;

public class BridgeServer extends UnicastRemoteObject implements BridgeServerAPI {
    public BridgeServer() throws RemoteException {
        super(0); // required to avoid the 'rmic' step, see below
    }

    public static void main(String[] args) throws Exception {
        System.out.println("RMI server started");

        try { //special exception handler for registry creation
            LocateRegistry.createRegistry(1099);
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }

        //Instantiate RmiServer
        BridgeServer server = new BridgeServer();

        // Bind this object instance to the name "BridgeServer"
        Naming.rebind("//localhost/BridgeServer", server);
        System.out.println("PeerServer bound in registry");
    }

    private final Map<ObjID, RlmHandle> handleRegistry = new HashMap<>();
    private final Map<ObjID, RlmActHandle> actHandleRegistry = new HashMap<>();
    private final Map<ObjID, RlmAvailableProduct> availableProductRegistry = new HashMap<>();
    private final HydrateActHandle hydrateActHandle = actHandle -> {
        final ObjID handleObjID = getIDOfRemoteObj(actHandle);
        if (!actHandleRegistry.containsKey(handleObjID)) {
            throw new RemoteException("Haven't heard of that ActHandle");
        }
        return actHandleRegistry.get(handleObjID);
    };

    private static ObjID getIDOfLocalObj(UnicastRemoteObject remote) {
        final UnicastServerRef serverRef = (UnicastServerRef) remote.getRef();
        return serverRef.getLiveRef().getObjID();
    }

    private static ObjID getIDOfRemoteObj(Remote remote) {
        final RemoteObjectInvocationHandler invocationHandler = (RemoteObjectInvocationHandler)Proxy.getInvocationHandler(remote);
        final UnicastRef remoteRef = (UnicastRef) invocationHandler.getRef();
        return remoteRef.getLiveRef().getObjID();
    }

    @Override
    public Handle Handle(String licLoc, String appPath, String license) throws RemoteException {
        try {
            final RlmHandle rlmHandle = new RlmHandle(licLoc, appPath, license);
            final HandleImpl handle = new HandleImpl(rlmHandle, hydrateActHandle);
            final ObjID objID = getIDOfLocalObj(handle);
            handleRegistry.put(objID, rlmHandle);
            return handle;
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public Handle Handle(String licLoc, String appPath, String license, int promise) throws RemoteException {
        try {
            final RlmHandle rlmHandle = new RlmHandle(licLoc, appPath, license, promise);
            final HandleImpl handle = new HandleImpl(rlmHandle, hydrateActHandle);
            final ObjID objID = getIDOfLocalObj(handle);
            handleRegistry.put(objID, rlmHandle);
            return handle;
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public Handle Handle(String licLoc, String appPath, String license, String[] env) throws RemoteException {
        try {
            final RlmHandle rlmHandle = new RlmHandle(licLoc, appPath, license, env);
            final HandleImpl handle = new HandleImpl(rlmHandle, hydrateActHandle);
            final ObjID objID = getIDOfLocalObj(handle);
            handleRegistry.put(objID, rlmHandle);
            return handle;
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public Handle Handle(String licLoc, String appPath, String license, String libName) throws RemoteException {
        try {
            final RlmHandle rlmHandle = new RlmHandle(licLoc, appPath, license, libName);
            final HandleImpl handle = new HandleImpl(rlmHandle, hydrateActHandle);
            final ObjID objID = getIDOfLocalObj(handle);
            handleRegistry.put(objID, rlmHandle);
            return handle;
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public Handle Handle(String licLoc, String appPath, String license, String libName, String[] env) throws RemoteException {
        try {
            final RlmHandle rlmHandle = new RlmHandle(licLoc, appPath, license, libName, env);
            final HandleImpl handle = new HandleImpl(rlmHandle, hydrateActHandle);
            final ObjID objID = getIDOfLocalObj(handle);
            handleRegistry.put(objID, rlmHandle);
            return handle;
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public ActHandle ActHandle(Handle handle) throws RemoteException {
        final ObjID handleObjID = getIDOfRemoteObj(handle);
        if (!handleRegistry.containsKey(handleObjID)) {
            throw new RemoteException("Haven't heard of that Handle");
        }
        final RlmHandle rh = handleRegistry.get(handleObjID);

        final RlmActHandle rlmActHandle = new RlmActHandle(rh);
        final ActHandleImpl actHandle = new ActHandleImpl(rlmActHandle);
        final ObjID objID = getIDOfLocalObj(actHandle);
        actHandleRegistry.put(objID, rlmActHandle);
        return actHandle;
    }

    @Override
    public ActInfo ActInfo(Handle handle, String url, String actKey) throws RemoteException {
        try {
            final ObjID objID = getIDOfRemoteObj(handle);
            if (!handleRegistry.containsKey(objID)) {
                throw new RemoteException("Haven't heard of that Handle");
            }
            final RlmHandle rh = handleRegistry.get(objID);

            final RlmActInfo rlmActHandle = new RlmActInfo(rh, url, actKey);
            return new ActInfoImpl(rlmActHandle);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public AvailableProduct AvailableProduct() throws RemoteException {
        final RlmAvailableProduct rAP = AvailableProductFactory.make();
        final AvailableProductImpl availableProduct = new AvailableProductImpl(rAP);
        final ObjID objID = getIDOfLocalObj(availableProduct);
        availableProductRegistry.put(objID, rAP);
        return availableProduct;
    }

    @Override
    public License License(Handle handle, String product, String version, int count) throws RemoteException {
        try {
            final ObjID objID = getIDOfRemoteObj(handle);
            if (!handleRegistry.containsKey(objID)) {
                throw new RemoteException("Haven't heard of that Handle");
            }
            final RlmHandle rh = handleRegistry.get(objID);

            final RlmLicense rLicense = new RlmLicense(rh, product, version, count);
            return new LicenseImpl(rLicense);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public License License(Handle handle, AvailableProduct product, String version, int count) throws RemoteException {
        try {
            final ObjID handleObjID = getIDOfRemoteObj(handle);
            if (!handleRegistry.containsKey(handleObjID)) {
                throw new RemoteException("Haven't heard of that Handle");
            }
            final RlmHandle rh = handleRegistry.get(handleObjID);

            final ObjID productObjID = getIDOfRemoteObj(product);
            if (!availableProductRegistry.containsKey(productObjID)) {
                throw new RemoteException("Haven't heard of that AvailableProduct");
            }
            final RlmAvailableProduct rAP = availableProductRegistry.get(productObjID);

            final RlmLicense rLicense = new RlmLicense(rh, rAP, version, count);
            return new LicenseImpl(rLicense);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }
}