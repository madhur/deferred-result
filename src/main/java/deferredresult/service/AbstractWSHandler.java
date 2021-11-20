package deferredresult.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AbstractWSHandler {

    private final ExecutorService executorService;
    private static final Logger logger = LoggerFactory.getLogger(AbstractWSHandler.class);

    @Autowired
    protected AbstractWSHandler() {
        this.executorService = Executors.newFixedThreadPool(100, new CustomizableThreadFactory(getClass().getSimpleName() + "-"));
    }

    protected void handle(Runnable runnable) {
        try {
            executorService.execute(runnable);
        }
        catch (Exception e) {
            logger.error("Unable to execute executor service due to {}", e);
        }

    }
}
