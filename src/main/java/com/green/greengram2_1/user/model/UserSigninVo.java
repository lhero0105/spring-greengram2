package com.green.greengram2_1.user.model;

import lombok.Data;

@Data
public class UserSigninVo {
    private int result;// 1 : 성공, 2: 아이디x 3: 비번다름
    private int iuser;
    private String nm;
    private String pic;
}
