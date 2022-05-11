package com.moldovan.uni.bookingsystem.repository;

import com.moldovan.uni.bookingsystem.domain.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends PagingAndSortingRepository<Room,Long> {
    @Query(value = "SELECT COUNT(*) FROM room", nativeQuery = true)
    int findTotalCount();
}
