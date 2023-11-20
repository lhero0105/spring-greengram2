package com.green.greengram2_1.user;

import com.green.greengram2_1.ResVo;
import com.green.greengram2_1.user.model.UserSignupDto;
import com.green.greengram2_1.user.model.UserSignupProcDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserMapper mapper;
    ResVo userSignup(UserSignupDto dto){
        String hashedPw = BCrypt.hashpw(dto.getUpw(), BCrypt.gensalt());
        log.info("hashedPw: {}", hashedPw);
        UserSignupProcDto pDto = UserSignupProcDto.builder()
                .uid(dto.getUid())
                .upw(hashedPw)
                .nm(dto.getNm())
                .pic(dto.getPic())
                .build();
        int affectedRows = mapper.insUser(pDto);
        log.info("result: {}", affectedRows);
        if(affectedRows == 0){
            return new ResVo(0);
        }
        return new ResVo(pDto.getIuser());
    }
}
