package uk.co.birchlabs.license_bridge.server.domain;

import com.reprisesoftware.rlm.RlmActHandle;
import com.reprisesoftware.rlm.RlmException;
import uk.co.birchlabs.license_bridge.common.domain.ActHandle;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ActHandleImpl extends UnicastRemoteObject implements ActHandle {
    private transient final RlmActHandle delegate;

    public ActHandleImpl(RlmActHandle delegate) throws RemoteException {
        super();
        this.delegate = delegate;
    }

    @Override
    public void setExtra(String extra) throws RemoteException {
        try {
            this.delegate.setExtra(extra);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public void setHostidList(String hostidList) throws RemoteException {
        try {
            this.delegate.setHostidList(hostidList);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public void setHostname(String hostname) throws RemoteException {
        try {
            this.delegate.setHostname(hostname);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public void setLogString(String logString) throws RemoteException {
        try {
            this.delegate.setLogString(logString);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public void setISV(String ISV) throws RemoteException {
        try {
            this.delegate.setISV(ISV);
        } catch (RlmException e) {
            throw new RemoteException(null, e);
        }
    }

    @Override
    public void setRehostable(boolean rehostable) {
        this.delegate.setRehostable(rehostable);
    }
}
