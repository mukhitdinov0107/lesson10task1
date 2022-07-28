package uz.pdp.lesson10task2.payload;

import lombok.Data;

@Data
public class RoomDto {

    private String number;

    private String floor;

    private String size;

    private Integer hotelId;
}
