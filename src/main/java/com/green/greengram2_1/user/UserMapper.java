package com.green.greengram2_1.user;

import com.green.greengram2_1.user.model.UserSigninDto;
import com.green.greengram2_1.user.model.UserSigninProcVo;
import com.green.greengram2_1.user.model.UserSigninVo;
import com.green.greengram2_1.user.model.UserSignupProcDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserSignupProcDto dto);
    UserSigninProcVo selUserById(String uid);
}
