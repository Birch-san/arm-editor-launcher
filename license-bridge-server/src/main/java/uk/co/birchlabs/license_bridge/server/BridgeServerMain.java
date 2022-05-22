package uk.co.birchlabs.license_bridge.server;

import uk.co.birchlabs.license_bridge.common.BridgeServerConstants;

public class BridgeServerMain {
    public static void main(String[] args) throws Exception {
        BridgeServerDeploy.deploy(BridgeServerConstants.serverName, BridgeServerConstants.registryPort);
    }
}
