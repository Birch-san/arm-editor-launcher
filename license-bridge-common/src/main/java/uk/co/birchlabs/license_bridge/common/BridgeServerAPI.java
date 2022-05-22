package uk.co.birchlabs.license_bridge.common;

import uk.co.birchlabs.license_bridge.common.domain.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BridgeServerAPI extends Remote {
    String healthCheck() throws RemoteException;
    Handle Handle(String licLoc, String appPath, String license) throws RemoteException;
    Handle Handle(String licLoc, String appPath, String license, int promise) throws RemoteException;
    Handle Handle(String licLoc, String appPath, String license, String[] env) throws RemoteException;
    Handle Handle(String licLoc, String appPath, String license, String libName) throws RemoteException;
    Handle Handle(String licLoc, String appPath, String license, String libName, String[] env) throws RemoteException;
    ActHandle ActHandle(Handle rh) throws RemoteException;
    ActInfo ActInfo(Handle rh, String url, String actKey) throws RemoteException;
    AvailableProduct AvailableProduct() throws RemoteException;
    License License(Handle handle, String product, String version, int count) throws RemoteException;
    License License(Handle handle, AvailableProduct product, String version, int count) throws RemoteException;
}
