package uz.pdp.lesson10task2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson10task2.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    Page<Room> findAllByHotelId(Integer hotel_id, Pageable pageable);
}
