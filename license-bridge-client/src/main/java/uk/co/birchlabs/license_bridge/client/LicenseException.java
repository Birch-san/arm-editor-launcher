package uk.co.birchlabs.license_bridge.client;

import com.reprisesoftware.rlm.RlmException;

public class LicenseException extends RlmException {
    public LicenseException(Throwable cause) {
        super(-1);
        this.initCause(cause);
    }
}
