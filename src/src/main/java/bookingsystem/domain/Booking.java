
package com.moldovan.uni.bookingsystem.domain;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    private String id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinTable(name = "bookings_responsible_person",
            joinColumns = {
                    @JoinColumn(name = "booking", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "person", referencedColumnName = "id")
            }
    )
    private Person responsiblePerson;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<Room> reservedRooms = new ArrayList<>();
    @Column(name ="check_in_date")
    private LocalDate checkInDate;
    @Column(name ="check_out_date")
    private LocalDate checkOutDate;
}