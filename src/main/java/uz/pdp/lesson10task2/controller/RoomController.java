package uz.pdp.lesson10task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson10task2.entity.Hotel;
import uz.pdp.lesson10task2.entity.Room;
import uz.pdp.lesson10task2.payload.RoomDto;
import uz.pdp.lesson10task2.repository.HotelRepository;
import uz.pdp.lesson10task2.repository.RoomRepository;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;

    @GetMapping("/getById/{hotelId}")
    public Page<Room> getStudentListForMinistry(@RequestParam int page, @PathVariable Integer hotelId) {

        Pageable pageable = PageRequest.of(page, 10);
        Page<Room> roomPage = roomRepository.findAllByHotelId(hotelId, pageable);
        return roomPage;
    }
    //READ
    @RequestMapping(value = "/Room", method = RequestMethod.GET)
    public List<Room> getUniversities() {
        List<Room> RoomList = roomRepository.findAll();
        return RoomList;
    }
    @PostMapping
    public String addRoom(@RequestBody RoomDto roomDto) {
        Room room = new Room();
        room.setFloor(roomDto.getFloor());
        room.setNumber(roomDto.getNumber());
        room.setSize(roomDto.getSize());

        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent())
            return "Hotel not found";
        room.setHotel(optionalHotel.get());
        roomRepository.save(room);
        return "Room saved";
    }

    //UPDATE
    @PutMapping
    public String editRoom(@PathVariable Integer id, @RequestBody RoomDto roomDto) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            room.setFloor(roomDto.getFloor());
            room.setNumber(roomDto.getNumber());
            room.setSize(roomDto.getSize());
            Optional<Hotel> optionalUniversity = hotelRepository.findById(roomDto.getHotelId());
            if (!optionalUniversity.isPresent()) {
                return "Room not found";
            }
            room.setHotel(optionalUniversity.get());
            roomRepository.save(room);
            return "room edited";
        }
        return "room not found";
    }


    //DELETE
    @DeleteMapping
    public String deleteRoom(@PathVariable Integer id) {
        try {
            roomRepository.deleteById(id);
            return "Room deleted";
        } catch (Exception e) {
            return "Error in deleting";
        }
    }
}
