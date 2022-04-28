package com.moldovan.uni.bookingsystem.dto;

import lombok.*;

import javax.validation.constraints.Size;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private int capacity;
    private boolean hasView;
}
