package com.green.greengram2_1.user.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSignupProcDto {
    private int iuser;
    private String uid;
    private String nm;
    private String pic;
    private String upw;
}
