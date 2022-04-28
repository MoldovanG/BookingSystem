
package bookingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class Person {

    private String personId;
    private String name;
    private String surname;
    private String IdentityCardIdentifier;
    private String address;
}