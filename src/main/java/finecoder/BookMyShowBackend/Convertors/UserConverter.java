package finecoder.BookMyShowBackend.Convertors;

import finecoder.BookMyShowBackend.Dto.UserResponseDto;
import finecoder.BookMyShowBackend.Models.UserEntity;
import finecoder.BookMyShowBackend.Dto.UserRequestDto;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {

    public static UserEntity UserDtoToEntity(UserRequestDto userRequestDto){
        UserEntity user = UserEntity.builder().name(userRequestDto.getName()).mobileNo(userRequestDto.getMobileNo()).build();
        return user;
    }

    public static List<UserResponseDto> getUserResponseList(List<UserEntity> userEntityList){
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();

        for(UserEntity user : userEntityList){
            UserResponseDto userResponseDto = UserResponseDto.builder().id(user.getId()).name(user.getName()).mobileNo(user.getMobileNo()).build();
            userResponseDtoList.add(userResponseDto);
        }

        return userResponseDtoList;
    }
}
