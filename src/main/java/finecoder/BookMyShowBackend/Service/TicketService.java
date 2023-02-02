package finecoder.BookMyShowBackend.Service;

import finecoder.BookMyShowBackend.Dto.ShowSeatResponseDto;
import finecoder.BookMyShowBackend.Dto.TicketResponseDto;
import finecoder.BookMyShowBackend.Enum.TicketStatus;
import finecoder.BookMyShowBackend.Models.*;
import finecoder.BookMyShowBackend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    ShowSeatRepository showSeatRepository;

    @Autowired
    TheatreSeatRepository theatreSeatRepository;

    @Autowired
    UserRepository userRepository;

    public List<ShowSeatResponseDto> getAvailableSeats(int showId){
        try {
            ShowEntity show = showRepository.findById(showId).get();
            int theatreId = show.getTheatre().getId();

            List<ShowSeatEntity> showSeatList = show.getShowSeats();
            List<ShowSeatResponseDto> showSeatResponseDtoList = new ArrayList<>();


            for(ShowSeatEntity showSeat:showSeatList){
                if(!showSeat.isBooked()){
                    ShowSeatResponseDto newShowSeat = ShowSeatResponseDto.builder().seatNo(showSeat.getSeatNo()).seatType(showSeat.getSeatType()).build();
                    newShowSeat.setPrice(theatreSeatRepository.getPrice(showSeat.getSeatNo(), theatreId));
                    showSeatResponseDtoList.add(newShowSeat);
                }
            }

            return showSeatResponseDtoList;
        }
        catch (Exception e){
            return null;
        }
    }

    public TicketResponseDto bookTicket(List<String> showSeats, int showId , int userId){
        try {
            System.out.println(1);
            //get show and theatre and movie and user
            ShowEntity show = showRepository.findById(showId).get();
            TheatreEntity theatre = show.getTheatre();
            MovieEntity movie = show.getMovie();
            UserEntity user = userRepository.findById(userId).get();

            System.out.println(2);
            // check availability
            for(String showSeat : showSeats){
                if(showSeatRepository.checkAvailable(showId,showSeat)) throw new Exception();
            }

            System.out.println(3);
            TicketEntity ticket = new TicketEntity();
            ticket.setBooked_at(new Date());
            ticket.setShow(show);
            ticket.setUser(user);

            List<ShowSeatEntity> ticketShowSeats = new ArrayList<>();
            int amount = 0;
            System.out.println(4);

            for(String showSeat : showSeats){
                ShowSeatEntity showSeatEntity = showSeatRepository.getSeatBySeatNo(showId,showSeat);
                showSeatEntity.setBooked(true);
                showSeatEntity.setTicket(ticket);
                showSeatEntity.setBooked_at(new Date());

                System.out.println(showSeat);
                amount+= (show.getMultiplier()* theatreSeatRepository.getPrice(showSeatEntity.getSeatNo(), theatre.getId()));
                System.out.println(amount);
                ticketShowSeats.add(showSeatEntity);
            }
            System.out.println(5);

            ticket.setAmount(amount);
            ticket.setBookedSeats(ticketShowSeats);
            ticket.setAlloted_seats(showSeats.toString());
            ticket.setTicketStatus(TicketStatus.BOOKED);
            ticketRepository.save(ticket);
            System.out.println(6);

            //create ticket and set attribute
            // relate to ticket

            user.getTickets().add(ticket);
            show.getTickets().add(ticket);
            userRepository.save(user);
            showRepository.save(show);
            System.out.println(7);

            //update showseats
            // set ticketResponseDto
            TicketResponseDto ticketResponseDto = TicketResponseDto.builder().ticketId(ticket.getId()).user_name(user.getName()).Movie_name(movie.getName()).theatre_name(theatre.getName()+" "+theatre.getCity()).show_time(show.getShowTime())
                    .show_Date(show.getShowDate()).alloted_seats(showSeats.toString()).amount(ticket.getAmount()).booked_on(ticket.getBooked_at()).build();

            return ticketResponseDto;

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<TicketResponseDto> getTicketsByUser(int userId){
        try {
            List<TicketEntity> ticketEntityList = userRepository.findById(userId).get().getTickets();
            List<TicketResponseDto> ticketResponseDtos = new ArrayList<>();

            for(TicketEntity ticket:ticketEntityList){
                TicketResponseDto ticketResponseDto = TicketResponseDto.builder().ticketId(ticket.getId()).user_name(ticket.getUser().getName()).Movie_name(ticket.getShow().getMovie().getName()).theatre_name(ticket.getShow().getTheatre().getName()+" "+ticket.getShow().getTheatre().getCity()).show_time(ticket.getShow().getShowTime())
                        .show_Date(ticket.getShow().getShowDate()).alloted_seats(ticket.getAlloted_seats()).amount(ticket.getAmount()).booked_on(ticket.getBooked_at()).build();
                ticketResponseDtos.add(ticketResponseDto);
            }

            return ticketResponseDtos;
        }
        catch (Exception e){
            return null;
        }
    }

    public String cancelTicket(int ticketId){
        try {
            TicketEntity ticket = ticketRepository.findById(ticketId).get();
            List<ShowSeatEntity> bookedSeats = ticket.getBookedSeats();

            for(ShowSeatEntity seat : bookedSeats){
                seat.setBooked(false);
                seat.setTicket(null);
                showSeatRepository.save(seat);
            }

            ticket.setAmount(0);
            ticket.setTicketStatus(TicketStatus.CANCELED);

            return "Ticket "+ticketId+" is canceled!";
        }
        catch (Exception e){
            return "Can't cancel the ticket";
        }
    }

}
