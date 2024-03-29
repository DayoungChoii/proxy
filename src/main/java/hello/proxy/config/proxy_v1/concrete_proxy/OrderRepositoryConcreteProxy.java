package hello.proxy.config.proxy_v1.concrete_proxy;

import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

public class OrderRepositoryConcreteProxy extends OrderRepositoryV2 {

    private final OrderRepositoryV2 target;
    private final LogTrace logTrace;

    public OrderRepositoryConcreteProxy(OrderRepositoryV2 target, LogTrace logTrace) {
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void save(String itemId) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = logTrace.begin("OrderRepository");
            if (itemId.equals("ex")) {
                throw new IllegalStateException();
            }
            target.save(itemId);
            logTrace.end(traceStatus);

        } catch (Exception e) {
            logTrace.exception(traceStatus, e);
            throw e;
        }
    }
}
