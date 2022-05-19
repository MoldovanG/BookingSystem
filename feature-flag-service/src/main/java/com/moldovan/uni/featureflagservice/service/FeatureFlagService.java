package com.moldovan.uni.featureflagservice.service;

import com.moldovan.uni.featureflagservice.models.FeatureFlag;
import com.moldovan.uni.featureflagservice.repository.FeatureFlagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeatureFlagService {
    @Autowired
    FeatureFlagRepository featureFlagRepository;

    public FeatureFlag save(FeatureFlag featureFlag) {
        return featureFlagRepository.save(featureFlag);
    }

    public Optional<FeatureFlag> findByName (String name){
       return featureFlagRepository.findById(name);
    }

    public List<FeatureFlag> getAll(){
        List<FeatureFlag> featureFlags = new ArrayList<>();
        featureFlagRepository.findAll().iterator().forEachRemaining(featureFlags::add);
        return featureFlags;
    }
}
