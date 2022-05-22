package uk.co.birchlabs.license_bridge.server.registry;

import com.reprisesoftware.rlm.RlmAvailableProduct;
import com.reprisesoftware.rlm.RlmHandle;
import uk.co.birchlabs.license_bridge.common.domain.AvailableProduct;
import uk.co.birchlabs.license_bridge.server.domain.AvailableProductImpl;
import uk.co.birchlabs.license_bridge.server.domain.HandleImpl;

import java.rmi.RemoteException;

public class AvailableProductRegistry extends Registry<AvailableProduct, AvailableProductImpl, RlmAvailableProduct> {
    public static final AvailableProductRegistry instance = new AvailableProductRegistry();

    public AvailableProductRegistry() {
        super(AvailableProduct.class, AvailableProductImpl.class, RlmAvailableProduct.class);
    }

    @Override
    public AvailableProduct dessicate(RlmAvailableProduct real) throws RemoteException {
        final AvailableProductImpl availableProduct = new AvailableProductImpl(real);
        AvailableProductRegistry.instance.register(availableProduct, real);
        return availableProduct;
    }

    public AvailableProduct dessicateUnchecked(RlmAvailableProduct real) {
        try {
            return dessicate(real);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
