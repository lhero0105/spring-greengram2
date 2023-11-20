package com.green.greengram2_1.user.model;

import lombok.Data;

@Data
public class UserSignupDto {
    private String uid;
    private String upw;
    private String nm;
    private String pic;
}