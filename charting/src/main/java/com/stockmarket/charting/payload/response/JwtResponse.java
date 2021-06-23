package com.stockmarket.charting.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private String mobileNumber;
    private String roles;

    public JwtResponse(String accessToken, Long id, String username, String email,String mobileNumber, String roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.mobileNumber= mobileNumber;
        this.email = email;
        this.roles = roles;
    }
}