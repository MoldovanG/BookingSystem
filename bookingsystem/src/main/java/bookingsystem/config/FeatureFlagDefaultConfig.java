package com.moldovan.uni.bookingsystem.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("bookingsystem")
@Getter
@Setter
public class FeatureFlagDefaultConfig {
    private int enableRegisterRoom;
}