package uk.co.birchlabs.license_bridge.client;

import uk.co.birchlabs.license_bridge.common.BridgeServerAPI;

import java.rmi.Naming;


public class BridgeClient {
    public static void main(String args[]) throws Exception {
        BridgeServerAPI server = (BridgeServerAPI)Naming.lookup("//localhost/BridgeServer");
        System.out.println(server.getMessage());
    }
}