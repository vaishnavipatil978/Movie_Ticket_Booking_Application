package finecoder.BookMyShowBackend.Dto;

import finecoder.BookMyShowBackend.Enum.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ShowSeatResponseDto {
    private String seatNo;
    private SeatType seatType;
    private int price ;
}
