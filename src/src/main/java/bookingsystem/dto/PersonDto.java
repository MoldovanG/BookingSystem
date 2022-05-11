package com.moldovan.uni.bookingsystem.dto;


import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    public static final String IdCardSerialNumberMatcher = "[A-Z]{2}-[0-9]{6}";
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Pattern(regexp = IdCardSerialNumberMatcher)
    private String identityCardIdentifier;
    @NotBlank
    private String address;
    @Email
    private String email;

    private String getUrl;
}