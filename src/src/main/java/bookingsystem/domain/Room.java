package bookingsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Room {
    private final int roomId;
    private final int capacity;
    private final boolean hasView;
}
