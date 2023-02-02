package finecoder.BookMyShowBackend.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "theatre_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheatreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String city;

    private String address;

    // List shows
    @OneToMany(mappedBy = "theatre",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("theatre")
    private List<ShowEntity> showList;

    //List Theatreseats
    @OneToMany(mappedBy = "theatre",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("theatre")
    private List<TheatreSeatEntity> theatreSeats;
}
