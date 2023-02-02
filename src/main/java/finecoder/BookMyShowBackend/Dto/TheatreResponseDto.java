package finecoder.BookMyShowBackend.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TheatreResponseDto {
    private int id;

    private String name;

    private String city;

    private String address;
}
