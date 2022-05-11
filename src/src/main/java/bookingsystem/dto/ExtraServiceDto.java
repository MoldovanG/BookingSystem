package com.moldovan.uni.bookingsystem.dto;

import com.moldovan.uni.bookingsystem.domain.ServiceType;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExtraServiceDto {
    @NotNull
    ServiceType type;
    @NotNull
    int cost;
}
