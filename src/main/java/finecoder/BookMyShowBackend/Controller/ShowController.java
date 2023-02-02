package finecoder.BookMyShowBackend.Controller;

import finecoder.BookMyShowBackend.Dto.ShowRequestDto;
import finecoder.BookMyShowBackend.Dto.ShowResponseDto;
import finecoder.BookMyShowBackend.Repository.TheatreRepository;
import finecoder.BookMyShowBackend.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;
    @Autowired
    private TheatreRepository theatreRepository;

    @PostMapping("/{theatreId}/add-show")
    public ResponseEntity addShow(@PathVariable int theatreId, @RequestBody ShowRequestDto showRequestDto){
        String response = showService.addShow(theatreId, showRequestDto);
        if(response==null) return new ResponseEntity("Can't add Show", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/get-all-shows")
    public ResponseEntity getAllShows(){
        List<ShowResponseDto> showList = showService.getAllShow();
        if(showList==null) return new ResponseEntity("Can't get Shows", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(showList,HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity getById(@PathVariable int id){
        ShowResponseDto show = showService.getShowById(id);
        if(show==null) return new ResponseEntity("No show exist!",HttpStatus.BAD_REQUEST);
        return new ResponseEntity(show,HttpStatus.OK);
    }

    @GetMapping("/get-shows-between/{city}/{date}")
    public ResponseEntity getShowsBetween(@PathVariable String city, @PathVariable LocalDate date, @RequestParam LocalTime
                                          start, @RequestParam LocalTime end){
        List<ShowResponseDto> showList = showService.getShowBetween(city,date,start,end);
        if(showList==null) return new ResponseEntity("Can't get Shows", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(showList,HttpStatus.OK);
    }

    @GetMapping("/get-by-movie")
    public ResponseEntity getShowsByMovie(@RequestParam int movieId){
        System.out.println(1);
        List<ShowResponseDto> showList = showService.getShowOfMovie(movieId);
        if(showList==null) return new ResponseEntity("Can't get Shows", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(showList,HttpStatus.OK);
    }

    @GetMapping("/get-by-theatre")
    public ResponseEntity getShowsByTheatre(@RequestParam int theatreId){
        List<ShowResponseDto> showList = showService.getShowsByTheatre(theatreId);
        if(showList==null) return new ResponseEntity("Can't get Shows", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(showList,HttpStatus.OK);
    }

    @GetMapping("/get-by-movie-and-theatre")
    public ResponseEntity getShowsByMovieAndTheatre(@RequestParam int movieId, @RequestParam int theatreId){
        List<ShowResponseDto> showList = showService.getShowsByMovieAndTheatre(movieId, theatreId);
        if(showList==null) return new ResponseEntity("Can't get Shows", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(showList,HttpStatus.OK);
    }

   @GetMapping("/get-movies-in-city")
    public ResponseEntity getShowsByMovieAndCity(@RequestParam int movieId, @RequestParam String city){
        List<ShowResponseDto> showList = showService.getMovieShowsInCity(city, movieId);
        if(showList==null) return new ResponseEntity("Can't get Shows", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(showList,HttpStatus.OK);
    }

    @DeleteMapping("/delete-show/{id}")
    public ResponseEntity deleteShow(@PathVariable int id){
        String response = showService.deleteShow(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
