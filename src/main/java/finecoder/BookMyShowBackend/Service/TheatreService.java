package finecoder.BookMyShowBackend.Service;

import finecoder.BookMyShowBackend.Convertors.TheatreConvertor;
import finecoder.BookMyShowBackend.Dto.TheatreRequestDto;
import finecoder.BookMyShowBackend.Dto.TheatreResponseDto;
import finecoder.BookMyShowBackend.Enum.SeatType;
import finecoder.BookMyShowBackend.Models.TheatreEntity;
import finecoder.BookMyShowBackend.Models.TheatreSeatEntity;
import finecoder.BookMyShowBackend.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {

    @Autowired
    TheatreRepository theatreRepository;

    public String addTheatre(TheatreRequestDto theatre){

        try {
            TheatreEntity newTheatre = TheatreEntity.builder().name(theatre.getName()).city(theatre.getCity()).address(theatre.getAddress()).build();
            newTheatre.setTheatreSeats(createTheatreSeats(theatre.getClassicRows(),theatre.getClassicRate(),theatre.getPlatinumRows(),theatre.getPlatinumRate(),newTheatre));
            theatreRepository.save(newTheatre);
            return "Theatre has been added!";
        }
        catch (Exception e){
            return "Theatre can't be added!";
        }

    }

    public List<TheatreSeatEntity> createTheatreSeats(int classicRows , int classicRate , int platinumRows, int platinumRate,TheatreEntity theatre) {

        List<TheatreSeatEntity> theatreSeatEntityList = new ArrayList<>();

        char seatCharacter = 'A';

        for (int i = 0; i < classicRows; i++) {
            for (int j = 1; j <= 10; j++) {
                String seatNo = seatCharacter + String.valueOf(j);
                TheatreSeatEntity newSeat = new TheatreSeatEntity(seatNo, classicRate, SeatType.CLASSIC, theatre);
                theatreSeatEntityList.add(newSeat);
            }
            seatCharacter++;
        }

        for (int i = 0; i < platinumRows; i++) {
            for (int j = 1; j <= 10; j++) {
                String seatNo = seatCharacter + String.valueOf(j);
                TheatreSeatEntity newSeat = new TheatreSeatEntity(seatNo, platinumRate, SeatType.PLATINUM, theatre);
                theatreSeatEntityList.add(newSeat);
            }
            seatCharacter++;
        }

        return theatreSeatEntityList;
    }

    public TheatreResponseDto getTheatreById(int id){
        try {
            TheatreEntity theatre = theatreRepository.findById(id).get();
            TheatreResponseDto theatreResponseDto = TheatreResponseDto.builder().id(theatre.getId()).name(theatre.getName()).city(theatre.getCity()).address(theatre.getAddress()).build();
            return theatreResponseDto;
        }
        catch (Exception e){
            return null;
        }
    }

    public List<TheatreResponseDto> getTheatreByName(String name){
        try {
            List<TheatreEntity> theatres = theatreRepository.findByName(name);
            List<TheatreResponseDto> theatreResponseDtos = TheatreConvertor.getListOfTheatreResponse(theatres);
            return theatreResponseDtos;
        }
        catch (Exception e){
            return null;
        }
    }

    public List<TheatreResponseDto> getTheatresByCity(String city){
        try {
            List<TheatreEntity> theatres = theatreRepository.findByCity(city);
            List<TheatreResponseDto> theatreResponseDtos = TheatreConvertor.getListOfTheatreResponse(theatres);
            return theatreResponseDtos;
        }
        catch (Exception e){
            return null;
        }
    }

    public List<TheatreSeatEntity> getTheatreSeats(int id){
        try {
            TheatreEntity theatre = theatreRepository.findById(id).get();
            return theatre.getTheatreSeats();
        }
        catch (Exception e){
            return null;
        }
    }

    public String deleteTheatre(int id){
        try {
            theatreRepository.deleteById(id);
            return "Theatre is deleted!";
        }
        catch (Exception e){
            return "Theatre not found!";
        }
    }

    public String updateSeatPrices(int id , String seatTypeString, int newPrice){
        try {
            TheatreEntity theatre = theatreRepository.findById(id).get();
            List<TheatreSeatEntity> theatreSeatList = theatre.getTheatreSeats();
            for(TheatreSeatEntity theatreSeat: theatreSeatList){
                if(theatreSeat.getSeatType().toString().equals(seatTypeString)){
                    theatreSeat.setRate(newPrice);
                }
            }
            theatre.setTheatreSeats(theatreSeatList);
            theatreRepository.save(theatre);
            return seatTypeString+" price is updated to "+String.valueOf(newPrice);
        }
        catch (Exception e){
            return "Theatre not updated!";
        }
    }

    public List<TheatreResponseDto> getAllTheatres(){
        try {
            List<TheatreEntity> theatreEntityList = theatreRepository.findAll();
            return TheatreConvertor.getListOfTheatreResponse(theatreEntityList);
        }
        catch (Exception e){
            return null;
        }
    }

    public TheatreResponseDto getTheatreByNameAndCity(String name, String city){
        try {
            TheatreEntity theatreEntity = theatreRepository.findTheatreByNameAndCity(name, city);
            TheatreResponseDto theatre = TheatreResponseDto.builder().id(theatreEntity.getId()).name(theatreEntity.getName()).city(theatreEntity.getCity()).address(theatreEntity.getAddress()).build();
            return theatre;
        }
        catch (Exception e){
            return null;
        }
    }

}
