package finecoder.BookMyShowBackend.Dto;

import lombok.Data;

@Data
public class TheatreRequestDto {
    private String name;

    private String city;

    private String address;

    private int classicRows;

    private int classicRate;

    private int platinumRows;

    private int platinumRate;
}
