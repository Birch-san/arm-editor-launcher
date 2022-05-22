package uk.co.birchlabs.license_bridge.server.registry;

import com.reprisesoftware.rlm.RlmConstants;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ObjID;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;
import static uk.co.birchlabs.license_bridge.server.registry.ObjIDExtractor.getIDOfLocalObj;
import static uk.co.birchlabs.license_bridge.server.registry.ObjIDExtractor.getIDOfRemoteObj;

public abstract class Registry<T extends Remote, U extends UnicastRemoteObject & Remote, V extends RlmConstants> {
    protected final Class<T> intf;
    protected final Class<U> proxyImpl;
    protected final Class<V> real;
    protected final Map<ObjID, V> registry = new HashMap<>();

    public Registry(Class<T> intf, Class<U> proxyImpl, Class<V> real) {
        this.intf = intf;
        this.proxyImpl = proxyImpl;
        this.real = real;
    }

    public void register(U proxy, V underlying) {
        final ObjID objID = getIDOfLocalObj(proxy);
        registry.put(objID, underlying);
    }

    public V get(T proxy) throws RemoteException {
        final ObjID handleObjID = getIDOfRemoteObj(proxy);
        if (!registry.containsKey(handleObjID)) {
            throw new RemoteException(format("Haven't heard of that %s", intf.getSimpleName()));
        }
        return registry.get(handleObjID);
    }

    public abstract T dessicate(V real) throws RemoteException;
}
