package finecoder.BookMyShowBackend.Dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class MovieResponseDto {
    private int id;

    private String name;

    private int duration;

    private Date releaseDate;
}
