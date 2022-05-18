package com.moldovan.uni.bookingsystem.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "capacity")
    private int capacity;

    @Column(name= "has_view")
    private boolean hasView;

    @Column(name = "price")
    @ApiModelProperty(notes= "must be in RON")
    private int price;
}
