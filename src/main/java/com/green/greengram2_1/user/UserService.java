package com.green.greengram2_1.user;

import com.green.greengram2_1.ResVo;
import com.green.greengram2_1.feed.FeedPicsMapper;
import com.green.greengram2_1.feed.model.FeedFavDto;
import com.green.greengram2_1.user.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserMapper mapper;
    private final FeedPicsMapper feedPicsMapper;


    public UserSigninVo userSignin(UserSigninDto dto){
        UserSigninProcVo procVo = mapper.selUserById(dto.getUid());
        UserSigninVo vo = new UserSigninVo();
        if(procVo != null) {
            String savedPw = procVo.getUpw(); // DB에서 가져온 비번
            boolean comparedPw = BCrypt.checkpw(dto.getUpw(), savedPw);
            // 첫번쨰 인자에 암호화 안된 비번 두번쨰 인자엔 DB의 암호화된 비번
            if(procVo == null){
                vo.setResult(2);
                return vo;
            }else if (comparedPw){
                vo.setResult(1);
                vo.setIuser(procVo.getIuser());
                vo.setNm(procVo.getNm());
                vo.setPic(procVo.getPic());
                return vo;
            }
        }
        vo.setResult(3);
        return vo;
    }
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
    public UserInfoVo getUserInfo(int targetIuser){
        return mapper.selUserInfo(targetIuser);
    }
    public ResVo patchUserProfile(UserPatchPicDto dto){
        int affective = feedPicsMapper.updFeedpic(dto);
        return new ResVo(affective);
    }
}
