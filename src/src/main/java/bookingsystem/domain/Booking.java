
package com.moldovan.uni.bookingsystem.domain;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person responsiblePerson;

    @Column(name ="check_in_date")
    private LocalDate checkInDate;
    @Column(name ="check_out_date")
    private LocalDate checkOutDate;

    @OneToMany
    @JoinTable(
            name="BOOKING_ROOMS",
            joinColumns = @JoinColumn( name="BOOKING_ID"),
            inverseJoinColumns = @JoinColumn( name="ROOM_ID")
    )
    private Set<Room> reservedRooms = new HashSet<>();

    @OneToMany
    @JoinTable(
            name="BOOKING_SERVICES",
            joinColumns = @JoinColumn( name="BOOKING_ID"),
            inverseJoinColumns = @JoinColumn( name="SERVICE_ID")
    )
    private Set<ExtraService> extraServices = new HashSet<>();
}