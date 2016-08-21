package ro.szzsa.keepalive;

import ro.szzsa.keepalive.model.Response;

/**
 *
 */
public interface ResponseInterpreter {

    void interpret(Response response);
}
