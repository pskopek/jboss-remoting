package org.jboss.cx.remoting.core.security.sasl;

import java.util.Map;

import javax.security.sasl.SaslClientFactory;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import javax.security.auth.callback.CallbackHandler;

/**
 *
 */
public final class SrpSaslClientFactoryImpl implements SaslClientFactory {
    public SaslClient createSaslClient(String[] mechanisms, String authorizationId, String protocol, String serverName, Map<String, ?> props, CallbackHandler cbh) throws SaslException {
        for (String mechanism : mechanisms) {
            if ("SRP".equals(mechanism)) {
                if (cbh == null) {
                    throw new SaslException("CallbackHandler required");
                }
                return new SrpSaslClientImpl(authorizationId, cbh, props);
            }
        }
        // We cannot provide any of the requested mechanisms
        return null;
    }

    public String[] getMechanismNames(Map<String, ?> props) {
        return new String[] { "SRP" };
    }
}