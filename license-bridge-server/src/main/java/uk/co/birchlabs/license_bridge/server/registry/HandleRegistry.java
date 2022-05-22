package uk.co.birchlabs.license_bridge.server.registry;

import com.reprisesoftware.rlm.RlmHandle;
import uk.co.birchlabs.license_bridge.common.domain.Handle;
import uk.co.birchlabs.license_bridge.server.domain.HandleImpl;

import java.rmi.RemoteException;

public class HandleRegistry extends Registry<Handle, HandleImpl, RlmHandle> {
    public static final HandleRegistry instance = new HandleRegistry();

    public HandleRegistry() {
        super(Handle.class, HandleImpl.class, RlmHandle.class);
    }

    @Override
    public Handle dessicate(RlmHandle real) throws RemoteException {
        final HandleImpl handle = new HandleImpl(real, ActHandleRegistry.instance.hydrateActHandle);
        register(handle, real);
        return handle;
    }
}
