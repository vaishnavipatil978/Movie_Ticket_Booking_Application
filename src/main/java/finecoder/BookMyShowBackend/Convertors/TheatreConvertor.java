package finecoder.BookMyShowBackend.Convertors;

import finecoder.BookMyShowBackend.Dto.TheatreResponseDto;
import finecoder.BookMyShowBackend.Models.TheatreEntity;

import java.util.ArrayList;
import java.util.List;

public class TheatreConvertor {

    public static List<TheatreResponseDto> getListOfTheatreResponse(List<TheatreEntity> theatreEntityList){

        List<TheatreResponseDto> responseDtoList = new ArrayList<>();

        for(TheatreEntity theatre:theatreEntityList){
            TheatreResponseDto theatreResponseDto = TheatreResponseDto.builder().id(theatre.getId()).name(theatre.getName()).city(theatre.getCity()).address(theatre.getAddress()).build();
            responseDtoList.add(theatreResponseDto);
        }

        return responseDtoList;
    }
}
