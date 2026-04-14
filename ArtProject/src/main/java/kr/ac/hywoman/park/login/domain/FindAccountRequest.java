package kr.ac.hywoman.park.login.domain;

import lombok.Getter;

@Getter
public class FindAccountRequest {
	private String name;
    private String phonenumber;
    private String email;
}
