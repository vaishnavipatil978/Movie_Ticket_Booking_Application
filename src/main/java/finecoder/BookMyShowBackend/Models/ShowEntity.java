package finecoder.BookMyShowBackend.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "show_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate showDate;

    private LocalTime showTime;

    private double multiplier;

    @CreationTimestamp
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdOn;

    @UpdateTimestamp
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedOn;

    //movie
    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("showList")
    private MovieEntity movie;

    //theatre
    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("showList")
    private TheatreEntity theatre;

    //list showSeat
    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    List<ShowSeatEntity> showSeats;

    //list tickets
    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("show")
    List<TicketEntity> tickets;


}
