package finecoder.BookMyShowBackend.Controller;

import finecoder.BookMyShowBackend.Dto.UserRequestDto;
import finecoder.BookMyShowBackend.Dto.UserResponseDto;
import finecoder.BookMyShowBackend.Models.UserEntity;
import finecoder.BookMyShowBackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity addUser(@RequestBody UserRequestDto userRequestDto){

        String response = userService.addUser(userRequestDto);
        return new ResponseEntity(response, HttpStatus.CREATED);

    }

    @GetMapping("/get-user-by-name/{name}")
    public ResponseEntity getUserByName(@PathVariable String name){
        List<UserEntity> users = userService.findUserByName(name);
        if(users==null) return new ResponseEntity("No user found",HttpStatus.BAD_REQUEST);
        return new ResponseEntity(users,HttpStatus.OK);
    }

    @GetMapping("/get-user-by-id/{id}")
    public ResponseEntity getUserById(@PathVariable int id){
        UserEntity user = userService.findUserById(id);
        if(user==null) return new ResponseEntity("No user found",HttpStatus.BAD_REQUEST);
        return new ResponseEntity(user,HttpStatus.OK);
    }

    @GetMapping("/get-all-users")
    public ResponseEntity getAllUsers(){
        List<UserResponseDto> userList = userService.findAllUsers();
        if(userList==null) return new ResponseEntity("No user found",HttpStatus.BAD_REQUEST);
        return new ResponseEntity(userList,HttpStatus.OK);
    }

    @PutMapping("/update-mobileNo")
    public ResponseEntity updateMobileNo(@RequestParam("id") int id, @RequestParam("mobileNo") String mobileNo){
        UserResponseDto user = userService.updateUser(id,mobileNo);
        return new ResponseEntity(user,HttpStatus.OK);
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
        String response = userService.deleteUser(id);
        return new ResponseEntity(response,HttpStatus.OK);
    }
}
