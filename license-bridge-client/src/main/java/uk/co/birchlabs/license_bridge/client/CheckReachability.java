package uk.co.birchlabs.license_bridge.client;

import uk.co.birchlabs.license_bridge.common.BridgeServerAPI;
import uk.co.birchlabs.license_bridge.common.BridgeServerConstants;

import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class CheckReachability {
    public static class ReachResult {
        public ReachResult(ReachOutcome outcome, Exception error, String malformedResponse) {
            this.outcome = outcome;
            this.error = error;
            this.malformedResponse = malformedResponse;
        }

        public static ReachResult success() {
            return new ReachResult(ReachOutcome.Success, null, null);
        }

        public static ReachResult malformedResponse(String message) {
            return new ReachResult(ReachOutcome.MalformedResponse, null, message);
        }

        public static ReachResult notBound(Exception error) {
            return new ReachResult(ReachOutcome.NotBound, error, null);
        }

        public static ReachResult error(Exception error) {
            return new ReachResult(ReachOutcome.Error, error, null);
        }

        public enum ReachOutcome {
            Success, MalformedResponse, NotBound, Error;
        }
        public final ReachOutcome outcome;
        public final String malformedResponse;
        public final Exception error;
    }

    public static ReachResult checkReachability() {
        try {
            final var server = (BridgeServerAPI) Naming.lookup(BridgeServerConstants.serverName);
            final var message = server.healthCheck();
            if (BridgeServerConstants.healthMessage.equals(message)) {
                return ReachResult.success();
            }
            return ReachResult.malformedResponse(message);
        } catch (NotBoundException | ConnectException e) {
            return ReachResult.notBound(e);
        } catch (MalformedURLException | RemoteException e) {
            return ReachResult.error(e);
        }
    }
}
