package finecoder.BookMyShowBackend.Controller;

import finecoder.BookMyShowBackend.Dto.MovieRequestDto;
import finecoder.BookMyShowBackend.Dto.MovieResponseDto;
import finecoder.BookMyShowBackend.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody MovieRequestDto movieRequestDto){
        String response = movieService.addMovie(movieRequestDto);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-movie-by-id/{id}")
    public ResponseEntity getMovieById(@PathVariable int id){
        try{
            return new ResponseEntity(movieService.getMovieById(id),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity("Movie doesn't exist!",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable String name){
        try{
            return new ResponseEntity(movieService.getMovieByName(name),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity("Movie doesn't exist!",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get-all-movies")
    public ResponseEntity getAllMovies(){
        List<MovieResponseDto> movieList = movieService.getAll();
        return new ResponseEntity(movieList,HttpStatus.OK);
    }

    @DeleteMapping("delete-movie/{id}")
    public ResponseEntity deleteMovie(@PathVariable int id){
        return new ResponseEntity(movieService.deleteMovie(id),HttpStatus.OK);
    }

}
