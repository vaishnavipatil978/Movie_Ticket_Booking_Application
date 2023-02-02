package finecoder.BookMyShowBackend.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@Builder
public class ShowResponseDto {

    private int showId;
    private String movieName;
    private String theatre;

    private LocalDate showDate;

    private LocalTime showTime;

    private double multiplier;
}
