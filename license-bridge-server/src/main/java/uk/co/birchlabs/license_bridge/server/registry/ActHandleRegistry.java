package uk.co.birchlabs.license_bridge.server.registry;

import com.reprisesoftware.rlm.RlmActHandle;
import uk.co.birchlabs.license_bridge.common.domain.ActHandle;
import uk.co.birchlabs.license_bridge.server.domain.ActHandleImpl;

import java.rmi.RemoteException;
import java.rmi.server.ObjID;

import static uk.co.birchlabs.license_bridge.server.registry.ObjIDExtractor.getIDOfRemoteObj;

public class ActHandleRegistry extends Registry<ActHandle, ActHandleImpl, RlmActHandle> {
    public static final ActHandleRegistry instance = new ActHandleRegistry();

    private ActHandleRegistry() {
        super(ActHandle.class, ActHandleImpl.class, RlmActHandle.class);
    }

    @Override
    public ActHandle dessicate(RlmActHandle real) throws RemoteException {
        final ActHandleImpl actHandle = new ActHandleImpl(real);
        register(actHandle, real);
        return actHandle;
    }

    @FunctionalInterface
    public interface HydrateActHandle {
        RlmActHandle make(ActHandle handle) throws RemoteException;
    }

    public final HydrateActHandle hydrateActHandle = actHandle -> {
        final ObjID handleObjID = getIDOfRemoteObj(actHandle);
        if (!this.registry.containsKey(handleObjID)) {
            throw new RemoteException("Haven't heard of that ActHandle");
        }
        return this.registry.get(handleObjID);
    };
}
