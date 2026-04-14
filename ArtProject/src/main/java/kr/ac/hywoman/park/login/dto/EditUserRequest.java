package kr.ac.hywoman.park.login.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditUserRequest {

    private String name;
    private String email;
    private String phonenumber;
    
}
