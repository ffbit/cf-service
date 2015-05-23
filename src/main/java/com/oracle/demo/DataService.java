package com.oracle.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.SECONDS;

import javax.annotation.ManagedBean;
import javax.annotation.PreDestroy;
import javax.ws.rs.Path;

@ManagedBean
@Path("data")
public class DataService {
    //@Inject
    ScheduledExecutorService shedPool = Executors.newScheduledThreadPool(1);

    public CompletableFuture<String> find(String path) {
        CompletableFuture<String> promise = new CompletableFuture<>();
        CompletableFuture
                .runAsync(() -> {
                    try {
                        promise.complete(new String(Files.readAllBytes(Paths.get(path))));
                    } catch (IOException e) {
                        promise.completeExceptionally(e);
                    }
                });
        // way #1
        shedPool.schedule(
                () -> promise.completeExceptionally(new TimeoutException()), 1, SECONDS);

        return promise;
    }
    public CompletableFuture<String> findAudience() {
        return find("audience");
    }
    public CompletableFuture<String> findImpression() {
        return find("impression");
    }
    @PreDestroy
    public void shutdown() {
        shedPool.shutdownNow();
    }
}
