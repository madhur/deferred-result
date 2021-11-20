package deferredresult.service;

import org.springframework.stereotype.Service;

@Service
public class NormalService {

    public String getDelayedResult() {

        try {
            System.out.println("Doing CPU task for 10 seconds : " + Thread.currentThread().getName());
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "normal";
    }
}
