package hello.proxy.config.proxy_v1.interface_proxy;

import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryProxy implements OrderRepositoryV1 {

    private final OrderRepositoryV1 target;
    private final LogTrace logTrace;

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
