package ro.szzsa.keepalive;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import ro.szzsa.keepalive.connector.Connector;
import ro.szzsa.keepalive.connector.impl.HttpConnector;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Task used to execute keep alive keepAliveCommand with fixed delay.
 */
public class KeepAliveTask {

    private final int initialDelay;

    private final int delay;

    private final ScheduledExecutorService scheduler;

    private final Connector connector;

    private final Runnable keepAliveCommand;

    /**
     * @param initialDelay initial delay in seconds
     * @param delay delay between keep alive executions in seconds
     * @param requestProvider the keep alive request provider
     * @param responseInterpreter the keep alive response interpreter
     */
    public KeepAliveTask(int initialDelay,
                         int delay,
                         RequestProvider requestProvider,
                         ResponseInterpreter responseInterpreter) {

        this.initialDelay = initialDelay;
        this.delay = delay;
        scheduler = Executors.newScheduledThreadPool(1);
        connector = new HttpConnector();
        keepAliveCommand = () -> responseInterpreter.interpret(connector.connect(requestProvider.getRequest()));
    }

    public void start() {
        scheduler.scheduleWithFixedDelay(keepAliveCommand, initialDelay, delay, SECONDS);
    }
}
