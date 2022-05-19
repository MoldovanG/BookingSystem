package com.moldovan.uni.featureflagservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class FeatureFlagAllreadyExists extends RuntimeException {
    public FeatureFlagAllreadyExists(String message) {
        super(message);
    }
}
