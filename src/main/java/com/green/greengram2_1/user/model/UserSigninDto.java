package com.green.greengram2_1.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSigninDto {
    private String uid;
    private String upw;
}
