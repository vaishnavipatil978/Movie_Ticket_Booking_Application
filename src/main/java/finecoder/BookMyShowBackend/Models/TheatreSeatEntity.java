package finecoder.BookMyShowBackend.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import finecoder.BookMyShowBackend.Enum.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheatreSeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public TheatreSeatEntity(String seatNo, int rate, SeatType seatType, TheatreEntity theatre) {
        this.seatNo = seatNo;
        this.rate = rate;
        this.seatType = seatType;
        this.theatre = theatre;
    }

    private String seatNo;

    private int rate;

    @Enumerated(value=EnumType.STRING)
    private SeatType seatType;

    //Theatre
    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("theatreSeats")
    TheatreEntity theatre;

}
