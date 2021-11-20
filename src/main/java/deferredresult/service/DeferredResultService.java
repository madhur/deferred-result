package deferredresult.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class DeferredResultService extends AbstractWSHandler {

    public CompletableFuture<String> getDelayedResult() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        handle(() -> {
            System.out.println("Doing CPU task for 10 seconds: " + Thread.currentThread().getName());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Returning " + Thread.currentThread().getName());
            completableFuture.complete("Deferred");
        });
        return completableFuture;
    }
}
