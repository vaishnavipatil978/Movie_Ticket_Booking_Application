package finecoder.BookMyShowBackend.Repository;

import finecoder.BookMyShowBackend.Models.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<MovieEntity,Integer> {

    @Query(value = "SELECT * FROM movie_table WHERE name=:mName",nativeQuery = true)
    public MovieEntity findMovieByName(String mName);
}
