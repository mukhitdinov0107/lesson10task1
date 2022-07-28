package uz.pdp.lesson10task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson10task2.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
