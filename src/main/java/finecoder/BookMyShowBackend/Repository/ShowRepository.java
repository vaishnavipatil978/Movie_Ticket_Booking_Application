package finecoder.BookMyShowBackend.Repository;

import finecoder.BookMyShowBackend.Models.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ShowRepository extends JpaRepository<ShowEntity,Integer> {

    @Query(value = "SELECT * FROM show_table WHERE theatre_id IN (select id from theatre_table where city=:showCity) AND show_date=:date",nativeQuery = true)
    public List<ShowEntity> getShowsByCityAndDate(String showCity , LocalDate date);

    @Query(value = "SELECT * FROM show_table WHERE theatre_id=:theatreId AND movie_id=:movieId",nativeQuery = true)
    public List<ShowEntity> getShowsByTheatreAndMovie(int theatreId, int movieId);

    @Query(value = " SELECT * FROM show_table WHERE theatre_id IN (select id from theatre_table where city=:showCity) AND movie_Id=:movieId",nativeQuery = true)
    public List<ShowEntity> getMovieShowsInCity(String showCity,int movieId);
}
