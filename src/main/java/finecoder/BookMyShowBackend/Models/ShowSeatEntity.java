package finecoder.BookMyShowBackend.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import finecoder.BookMyShowBackend.Enum.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowSeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private boolean booked;

    private Date booked_at;


    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("showSeats")
    private ShowEntity show;


    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("bookedSeats")
    private TicketEntity ticket;
}
