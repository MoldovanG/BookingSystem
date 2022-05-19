package com.moldovan.uni.featureflagservice.repository;

import com.moldovan.uni.featureflagservice.models.FeatureFlag;
import org.springframework.data.repository.CrudRepository;

public interface FeatureFlagRepository extends CrudRepository<FeatureFlag, String> {
}
