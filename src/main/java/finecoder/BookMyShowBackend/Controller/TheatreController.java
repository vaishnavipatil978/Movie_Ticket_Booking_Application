package finecoder.BookMyShowBackend.Controller;

import finecoder.BookMyShowBackend.Dto.TheatreRequestDto;
import finecoder.BookMyShowBackend.Dto.TheatreResponseDto;
import finecoder.BookMyShowBackend.Models.TheatreSeatEntity;
import finecoder.BookMyShowBackend.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

    @Autowired
    TheatreService theatreService;

    @PostMapping("/add-theatre")
    public ResponseEntity addTheatre(@RequestBody TheatreRequestDto theatreRequestDto){
        String response = theatreService.addTheatre(theatreRequestDto);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-theatre-by-id/{id}")
    public ResponseEntity getTheatreById(@PathVariable int id){
        TheatreResponseDto theatre = theatreService.getTheatreById(id);

        if(theatre==null) return new ResponseEntity("No theatre present",HttpStatus.BAD_REQUEST);

        return new ResponseEntity(theatre,HttpStatus.OK);
    }

    @GetMapping("/get-theatre-by-name/{name}")
    public ResponseEntity getTheatreByName(@PathVariable String name){
        List<TheatreResponseDto> theatreList = theatreService.getTheatreByName(name);

        if(theatreList==null) return new ResponseEntity("No such theatre present",HttpStatus.BAD_REQUEST);

        return new ResponseEntity(theatreList,HttpStatus.OK);
    }

    @GetMapping("/get-theatre-by-city/{city}")
    public ResponseEntity getTheatreByCity(@PathVariable String city){
        List<TheatreResponseDto> theatreList = theatreService.getTheatresByCity(city);

        if(theatreList==null) return new ResponseEntity("No such theatre present",HttpStatus.BAD_REQUEST);

        return new ResponseEntity(theatreList,HttpStatus.OK);
    }

    @GetMapping("/get-theatre-by-name-and-city/{name}/{city}")
    public ResponseEntity getTheatreByNameAndCity(@PathVariable String name, @PathVariable String city){
        TheatreResponseDto theatre = theatreService.getTheatreByNameAndCity(name, city);

        if(theatre==null) return new ResponseEntity("No such theatre present",HttpStatus.BAD_REQUEST);

        return new ResponseEntity(theatre,HttpStatus.OK);
    }

    @GetMapping("/get-Theatre-seats/{id}")
    public ResponseEntity getTheatreSeats(@PathVariable int id){
        List<TheatreSeatEntity> theatreSeatEntityList = theatreService.getTheatreSeats(id);

        if(theatreSeatEntityList==null) return new ResponseEntity("No Theatre Seat Exist",HttpStatus.BAD_REQUEST);

        return new ResponseEntity(theatreSeatEntityList,HttpStatus.OK);
    }

    @GetMapping("/get-all-Theatres")
    public ResponseEntity getAllTheatres(){
        List<TheatreResponseDto> theatres = theatreService.getAllTheatres();
        return new ResponseEntity(theatres,HttpStatus.OK);
    }

    @PutMapping("/update-prices/{id}")
    public ResponseEntity updatePrices(@PathVariable int id,@RequestParam String seatType, @RequestParam int newPrice ){
        String response = theatreService.updateSeatPrices(id,seatType,newPrice);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete-theatre/{id}")
    public ResponseEntity deleteTheatre(@PathVariable int id){
        String response = theatreService.deleteTheatre(id);
        return new ResponseEntity(response,HttpStatus.OK);
    }

}
