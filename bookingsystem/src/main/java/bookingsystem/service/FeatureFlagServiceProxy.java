package com.moldovan.uni.bookingsystem.service;

import com.moldovan.uni.bookingsystem.domain.FeatureFlag;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "feature-flag-service")
@RibbonClient(name = "feature-flag-service")
public interface FeatureFlagServiceProxy {
    @GetMapping("/featureflags/{name}")
    FeatureFlag findFeatureFlag(@PathVariable String name);
}