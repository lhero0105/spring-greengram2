package com.green.greengram2_1.user;

import com.green.greengram2_1.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserSignupProcDto dto);
    UserSigninProcVo selUserById(String uid);
    UserInfoVo selUserInfo(int targetIuser);

}
