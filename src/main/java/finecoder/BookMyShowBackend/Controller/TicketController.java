package finecoder.BookMyShowBackend.Controller;

import finecoder.BookMyShowBackend.Dto.ShowSeatResponseDto;
import finecoder.BookMyShowBackend.Dto.TicketResponseDto;
import finecoder.BookMyShowBackend.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/get-available-seats/{showId}")
    public ResponseEntity getAvailable(@PathVariable int showId){
        List<ShowSeatResponseDto> list = ticketService.getAvailableSeats(showId);
        if(list==null) return new ResponseEntity("No seat Available!", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PostMapping("/book-ticket/show/{showId}/user/{userId}")
    public ResponseEntity bookTicket(@PathVariable int showId,@PathVariable int userId, @RequestBody List<String> seats){
        TicketResponseDto ticket = ticketService.bookTicket(seats,showId,userId);
        if(ticket==null) return new ResponseEntity("Can't book ticket!", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(ticket,HttpStatus.OK);
    }

    @PutMapping("/cancel-ticket/{ticketId}")
    public ResponseEntity cancelTicket(@PathVariable int ticketId){
        String response = ticketService.cancelTicket(ticketId);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @GetMapping("/get-user-tickets/{userId}")
    public ResponseEntity getUserTickets(@PathVariable int userId){
        List<TicketResponseDto> ticketResponseDtoList = ticketService.getTicketsByUser(userId);
        if(ticketResponseDtoList==null) return new ResponseEntity("No ticket Booked!",HttpStatus.BAD_REQUEST);
        return new ResponseEntity(ticketResponseDtoList,HttpStatus.OK);
    }

}
