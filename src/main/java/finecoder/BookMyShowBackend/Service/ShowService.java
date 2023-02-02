package finecoder.BookMyShowBackend.Service;

import finecoder.BookMyShowBackend.Convertors.ShowConvertor;
import finecoder.BookMyShowBackend.Dto.ShowRequestDto;
import finecoder.BookMyShowBackend.Dto.ShowResponseDto;
import finecoder.BookMyShowBackend.Dto.ShowSeatResponseDto;
import finecoder.BookMyShowBackend.Models.*;
import finecoder.BookMyShowBackend.Repository.MovieRepository;
import finecoder.BookMyShowBackend.Repository.ShowRepository;
import finecoder.BookMyShowBackend.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ShowRepository showRepository;

    public String addShow(int theatreId, ShowRequestDto showRequestDto) {
        try {
            TheatreEntity theatre = theatreRepository.findById(theatreId).get();

            MovieEntity movie = movieRepository.findById(showRequestDto.getMovieId()).get();

            ShowEntity show = new ShowEntity();
            show.setShowDate(showRequestDto.getShowDate());
            show.setShowTime(showRequestDto.getShowTime());
            show.setMultiplier(showRequestDto.getMultiplier());
            show.setMovie(movie);
            show.setTheatre(theatre);

            List<ShowSeatEntity> showSeats = createShowSeats(theatre.getTheatreSeats(), show);

            show.setShowSeats(showSeats);

            theatre.getShowList().add(show);
            movie.getShowList().add(show);

            showRepository.save(show);
            movieRepository.save(movie);
            theatreRepository.save(theatre);

            return "Show Successfully added";
        } catch (Exception e) {
            return "Can't add show";
        }
    }

    public List<ShowSeatEntity> createShowSeats(List<TheatreSeatEntity> theatreSeatList, ShowEntity show) {
        List<ShowSeatEntity> showSeatEntityList = new ArrayList<>();

        for (TheatreSeatEntity theatreSeat : theatreSeatList) {
            ShowSeatEntity showSeat = ShowSeatEntity.builder().seatNo(theatreSeat.getSeatNo()).seatType(theatreSeat.getSeatType()).booked(false).show(show).build();
            showSeatEntityList.add(showSeat);
        }

        return showSeatEntityList;
    }

    public ShowResponseDto getShowById(int id){
        try {
            ShowEntity show = showRepository.findById(id).get();
            TheatreEntity theatre = show.getTheatre();
            ShowResponseDto newShow = ShowResponseDto.builder().showDate(show.getShowDate()).showTime(show.getShowTime()).showId(show.getId()).movieName(show.getMovie().getName()).multiplier(show.getMultiplier()).theatre(theatre.getName()+" "+ theatre.getAddress()+" "+ theatre.getCity()).build();
            return newShow;
        }
        catch (Exception e){
            return null;
        }
    }


    public List<ShowResponseDto> getAllShow() {

        try {
            List<ShowEntity> shows = showRepository.findAll();
            return ShowConvertor.convertToResponseDtoList(shows);
        } catch (Exception e) {
            return null;
        }
    }

    public List<ShowResponseDto> getShowBetween(String city, LocalDate date, LocalTime start , LocalTime end){
        try {
            System.out.println(1);
            List<ShowEntity> showEntityList = showRepository.getShowsByCityAndDate(city,date);
            List<ShowEntity> finalList = new ArrayList<>();
            System.out.println(2);

            for(ShowEntity show : showEntityList){
                System.out.println(3);
                LocalTime showTime = show.getShowTime();
                if(showTime.equals(start)|| (showTime.isAfter(start) && showTime.isBefore(end)) || showTime.equals(end)){
                    finalList.add(show);
                }
                System.out.println(4);
            }

            return ShowConvertor.convertToResponseDtoList(finalList);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<ShowResponseDto> getShowOfMovie(int movieId){
        try {
            List<ShowEntity> showList = movieRepository.findById(movieId).get().getShowList();
            return ShowConvertor.convertToResponseDtoList(showList);
        }
        catch (Exception e){
            return null;
        }
    }

    public List<ShowResponseDto> getShowsByTheatre(int theatreId){
        try {
            List<ShowEntity> showList = theatreRepository.findById(theatreId).get().getShowList();
            return ShowConvertor.convertToResponseDtoList(showList);
        }
        catch (Exception e){
            return null;
        }
    }

    public String deleteShow(int showId){
        showRepository.deleteById(showId);
        return "show "+ showId + " is deleted";
    }

    public List<ShowResponseDto> getShowsByMovieAndTheatre(int movieId, int theatreId){
       try {
           List<ShowEntity> showList = showRepository.getShowsByTheatreAndMovie(theatreId,movieId);
           return ShowConvertor.convertToResponseDtoList(showList);
       }
       catch (Exception e){
           return null;
       }
    }

    public List<ShowResponseDto> getMovieShowsInCity(String city, int movieId){
        try {
            System.out.println(1);
            List<ShowEntity> showList = showRepository.getMovieShowsInCity(city, movieId);
            System.out.println(2);
            return ShowConvertor.convertToResponseDtoList(showList);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}

