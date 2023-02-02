package finecoder.BookMyShowBackend.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import finecoder.BookMyShowBackend.Enum.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String alloted_seats;

    private int amount;

    private Date booked_at;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("tickets")
    private UserEntity user;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("tickets")
    private ShowEntity show;


    @OneToMany(mappedBy = "ticket",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("ticket")
    private List<ShowSeatEntity> bookedSeats;
}
