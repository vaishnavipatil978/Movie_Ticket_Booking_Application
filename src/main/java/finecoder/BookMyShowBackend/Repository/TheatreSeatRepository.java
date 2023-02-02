package finecoder.BookMyShowBackend.Repository;

import finecoder.BookMyShowBackend.Models.TheatreSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TheatreSeatRepository extends JpaRepository<TheatreSeatEntity, Integer> {

    @Query(value = "select rate from theatre_seat_entity where seat_no=:seatNo and theatre_id=:theatreId",nativeQuery = true)
    public int getPrice(String seatNo, int theatreId);

}
