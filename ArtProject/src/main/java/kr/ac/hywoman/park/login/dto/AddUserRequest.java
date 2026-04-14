package kr.ac.hywoman.park.login.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {

    private String username;
    private String password;
    private String email;
    private String name;
    private String phonenumber;
}
