
package com.moldovan.uni.bookingsystem.domain;

import lombok.*;

import javax.persistence.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {

    @Id
    private String id;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "serial_number")
    private String IdentityCardIdentifier;
    @Column(name = "address")
    private String address;
}