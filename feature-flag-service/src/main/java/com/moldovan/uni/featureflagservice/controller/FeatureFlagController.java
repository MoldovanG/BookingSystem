package com.moldovan.uni.featureflagservice.controller;

import com.moldovan.uni.featureflagservice.exception.FeatureFlagAllreadyExists;
import com.moldovan.uni.featureflagservice.exception.FeatureFlagNotFound;
import com.moldovan.uni.featureflagservice.models.FeatureFlag;
import com.moldovan.uni.featureflagservice.service.FeatureFlagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@Slf4j
public class FeatureFlagController extends RepresentationModel<FeatureFlag> {
    @Autowired
    FeatureFlagService featureFlagService;

    @GetMapping("/featureflags")
    CollectionModel<FeatureFlag> getAll(){
        List<FeatureFlag> featureFlags = featureFlagService.getAll();
        for (final FeatureFlag featureFlag : featureFlags) {
            Link selfLink = linkTo(methodOn(FeatureFlagController.class).getFeatureFlag(featureFlag.getName())).withSelfRel();
            featureFlag.add(selfLink);
        }
        Link link = linkTo(methodOn(FeatureFlagController.class).getAll()).withSelfRel();
        CollectionModel<FeatureFlag> result = CollectionModel.of(featureFlags, link);
        return result;

    }

    @GetMapping("/featureflags/{name}")
    RepresentationModel<FeatureFlag> getFeatureFlag(
            @PathVariable String name
    ){
        Optional<FeatureFlag> featureFlagOptional = featureFlagService.findByName(name);

        if( featureFlagOptional.isEmpty()){
            throw new FeatureFlagNotFound("Feature flag with name : " + name + " was not found");
        }
        FeatureFlag featureFlag = featureFlagOptional.get();
        Link selfLink = linkTo(methodOn(FeatureFlagController.class).getFeatureFlag(featureFlag.getName())).withSelfRel();
        featureFlag.add(selfLink);
        return featureFlag;
    }

    @PutMapping("/featurelags/{name}/{value}")
    RepresentationModel<FeatureFlag> updateFlag(@PathVariable String name,
                           @PathVariable Boolean value){
        Optional<FeatureFlag> featureFlagOptional = featureFlagService.findByName(name);

        if( featureFlagOptional.isEmpty()){
            throw new FeatureFlagNotFound("Feature flag with name : " + name + " was not found");
        }
        FeatureFlag featureFlag = featureFlagOptional.get();
        featureFlag.setValue(value);
        FeatureFlag result = featureFlagService.save(featureFlag);
        Link selfLink = linkTo(methodOn(FeatureFlagController.class).getFeatureFlag(result.getName())).withSelfRel();
        result.add(selfLink);
        return result;
    }

    @PostMapping("/featurelags/{name}/{value}")
    RepresentationModel<FeatureFlag> addFlag(@PathVariable String name,
                           @PathVariable Boolean value){
        Optional<FeatureFlag> featureFlagOptional = featureFlagService.findByName(name);
        if( featureFlagOptional.isPresent()){
            throw new FeatureFlagAllreadyExists("Feature flag with name : " + name + " allready exists");
        }
        FeatureFlag featureFlag = new FeatureFlag();
        featureFlag.setName(name);
        featureFlag.setValue(value);
        FeatureFlag result = featureFlagService.save(featureFlag);
        Link selfLink = linkTo(methodOn(FeatureFlagController.class).getFeatureFlag(result.getName())).withSelfRel();
        result.add(selfLink);
        return result;
    }



}
