package com.test.testapi.controllers.models;

import java.time.Instant;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Manthan Dave
 */
@Builder
@Getter
public class GreetingResponse {
    private String message;
    private final Instant time = Instant.now();
}
