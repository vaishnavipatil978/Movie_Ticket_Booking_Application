package finecoder.BookMyShowBackend.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequestDto {

    private String name;

    private String mobileNo;

}
