package com.moldovan.uni.featureflagservice.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class FeatureFlag extends RepresentationModel<FeatureFlag> {
    @Id
    @Column(name="name")
    private String name;

    @Column(name="value")
    private boolean value;
}
