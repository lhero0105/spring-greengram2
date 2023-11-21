package com.green.greengram2_1.feed;

import com.green.greengram2_1.ResVo;
import com.green.greengram2_1.feed.model.FeedInsDto;
import com.green.greengram2_1.feed.model.FeedInsProcDto;
import com.green.greengram2_1.feed.model.FeedSelDto;
import com.green.greengram2_1.feed.model.FeedSelVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedService {
    private final FeedMapper mapper;
    private final FeedPicsMapper picsMapper;
    public ResVo InsFeedDto(FeedInsDto dto){
        if(dto.getPics().size() == 0){
            return new ResVo(2); // 사진이 없으면 2
        }
        FeedInsProcDto pDto = new FeedInsProcDto(dto);
        int feedAffectedRows = mapper.insFeed(pDto);
        log.info("feedAffectedRows : ",feedAffectedRows);

        if(feedAffectedRows == 0 || pDto.getIfeed() == 0){
            return new ResVo(0); // 사진 등록이
        }
        int feedPicsAffectedRows = mapper.insFeedPic(pDto);
        if(feedPicsAffectedRows != dto.getPics().size()){ // 트랜젝션이면 롤백
            return new ResVo(3); // 사진 등록이 제대로 안됨
        }
        return new ResVo(pDto.getIfeed());
    }

    // n+1 이슈
    public List<FeedSelVo> getFeedAll(FeedSelDto dto){
        List<FeedSelVo> list = mapper.selFeedAll(dto);
        for ( FeedSelVo vo : list ) {
            List<String> pics = picsMapper.selFeedPicsAll(vo.getIfeed());
            vo.setPics(pics);
        }
        return list;
    }
}
