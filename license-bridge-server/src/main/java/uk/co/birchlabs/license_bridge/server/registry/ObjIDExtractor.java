package uk.co.birchlabs.license_bridge.server.registry;

import sun.rmi.server.UnicastRef;
import sun.rmi.server.UnicastServerRef;

import java.lang.reflect.Proxy;
import java.rmi.Remote;
import java.rmi.server.ObjID;
import java.rmi.server.RemoteObjectInvocationHandler;
import java.rmi.server.UnicastRemoteObject;

public class ObjIDExtractor {
    public static ObjID getIDOfLocalObj(UnicastRemoteObject remote) {
        final UnicastServerRef serverRef = (UnicastServerRef) remote.getRef();
        return serverRef.getLiveRef().getObjID();
    }

    public static ObjID getIDOfRemoteObj(Remote remote) {
        final RemoteObjectInvocationHandler invocationHandler = (RemoteObjectInvocationHandler) Proxy.getInvocationHandler(remote);
        final UnicastRef remoteRef = (UnicastRef) invocationHandler.getRef();
        return remoteRef.getLiveRef().getObjID();
    }
}
