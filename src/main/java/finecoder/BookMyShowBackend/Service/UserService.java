package finecoder.BookMyShowBackend.Service;

import finecoder.BookMyShowBackend.Convertors.UserConverter;
import finecoder.BookMyShowBackend.Dto.UserResponseDto;
import finecoder.BookMyShowBackend.Models.UserEntity;
import finecoder.BookMyShowBackend.Repository.UserRepository;
import finecoder.BookMyShowBackend.Dto.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String addUser(UserRequestDto userRequestDto){

        try{
            UserEntity user = UserConverter.UserDtoToEntity(userRequestDto);
            userRepository.save(user);
            return "User successfully added!";
        }
        catch(Exception e){
            return "User already exist! ";
        }

    }

    public List<UserEntity> findUserByName( String name ){
        try{
            return userRepository.findUserByName(name);
        }
        catch(Exception e){
            return null;
        }
    }

    public List<UserResponseDto> findAllUsers(){
        try {
            List<UserEntity> userEntityList = userRepository.findAll();
            return UserConverter.getUserResponseList(userEntityList);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public UserEntity findUserById(int id){
        return userRepository.findById(id).get();
    }

    public UserResponseDto updateUser(int id, String mobileNo){
        try{
            UserEntity user = userRepository.findById(id).get();
            user.setMobileNo(mobileNo);
            userRepository.save(user);

            UserResponseDto userResponseDto = UserResponseDto.builder().id(user.getId()).name(user.getName()).mobileNo(user.getMobileNo()).build();
            return userResponseDto;
        }
        catch(Exception e){
            return null;
        }
    }

    public String deleteUser(int id){
        userRepository.deleteById(id);
        return "User deleted!";
    }

}
