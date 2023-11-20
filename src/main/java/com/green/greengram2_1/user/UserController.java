package com.green.greengram2_1.user;

import com.green.greengram2_1.ResVo;
import com.green.greengram2_1.user.model.UserSigninDto;
import com.green.greengram2_1.user.model.UserSigninVo;
import com.green.greengram2_1.user.model.UserSignupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    @PostMapping("/signin")
    public UserSigninVo postUserSignin(@RequestBody UserSigninDto dto){
        return service.userSignin(dto);
    }
    @PostMapping("/signup")
    public ResVo postUserSignup(@RequestBody UserSignupDto dto){
        // System.out.println(dto);
        log.info("dto: {}", dto);
        return service.userSignup(dto); // ResVo객체에 insert한 레코드 pk값을 담아서 응답처리
    }
}
