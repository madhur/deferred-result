package deferredresult.controller;

import deferredresult.service.DeferredResultService;
import deferredresult.service.NormalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;

@RestController
public class DeferredResultController {

    @Autowired
    private DeferredResultService deferredResultService;

    @Autowired
    private NormalService normalService;

    @GetMapping("/deferred")
    public DeferredResult<ResponseEntity<String>> getDeferredResult() {
        System.out.println("Received request on: " + Thread.currentThread().getName());
        CompletableFuture<String> completableFuture = deferredResultService.getDelayedResult();
        DeferredResult<ResponseEntity<String>> deferredResult = new DeferredResult<>();
        completableFuture.thenAccept(result -> {
            if (deferredResult.hasResult()) {
                return;
            }
            deferredResult.setResult(new ResponseEntity<>(result, HttpStatus.OK));
        });
        completableFuture.exceptionally(e -> {
            return null;
        });
        return deferredResult;
    }

    @GetMapping("/normal")
    public ResponseEntity<String> getNormalResult() {
        String result = normalService.getDelayedResult();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
