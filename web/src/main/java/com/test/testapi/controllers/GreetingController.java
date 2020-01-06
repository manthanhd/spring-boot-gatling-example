package com.test.testapi.controllers;

import com.test.testapi.controllers.models.GreetingResponse;
import java.util.concurrent.CountDownLatch;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Manthan Dave
 */
@RestController
public class GreetingController {

    private final CountDownLatch countTillFailure = new CountDownLatch(1000);

    @GetMapping("/greeting")
    public ResponseEntity<GreetingResponse> getGreeting() {
        countTillFailure.countDown();
        if (countTillFailure.getCount() == 0)
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        else
            return ResponseEntity.ok(GreetingResponse.builder().message("hello world").build());
    }

}
