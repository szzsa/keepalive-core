package ro.szzsa.keepalive.connector;

import ro.szzsa.keepalive.model.Request;
import ro.szzsa.keepalive.model.Response;

/**
 *
 */
public interface Connector {

    Response connect(Request request);
}
