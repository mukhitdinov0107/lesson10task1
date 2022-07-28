package uz.pdp.lesson10task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson10task2.entity.Hotel;
import uz.pdp.lesson10task2.repository.HotelRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;


    //READ
    @GetMapping
    public List<Hotel> getUniversities() {
        List<Hotel> HotelList = hotelRepository.findAll();
        return HotelList;
    }
    @PostMapping
    public String addHotel(@RequestBody Hotel hotel) {


        Hotel Hotel = new Hotel();
        Hotel.setName(hotel.getName());
        hotelRepository.save(Hotel);

        return "Hotel added";
    }

    //UPDATE
    @PutMapping("/{id}")
    public String editHotel(@PathVariable Integer id, @RequestBody Hotel hotel) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()) {
            Hotel hotel1 = optionalHotel.get();
            hotel.setName(hotel.getName());
            hotelRepository.save(hotel1);
            return "Hotel edited";
        }
        return "Hotel not found";
    }


    //DELETE
    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable Integer id){
        hotelRepository.deleteById(id);
        return "Hotel deleted";
    }
}
