package com.green.greengram2_1.feed;

import com.green.greengram2_1.ResVo;
import com.green.greengram2_1.feed.model.*;
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
    private final FeedFavMapper feedFavMapper;
    private final FeedCommentMapper feedCommnetMapper;
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
        int feedPicsAffectedRows = picsMapper.insFeedPic(pDto);
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

            List<FeedCommentSelVo> comments = feedCommnetMapper.selCommentAll(FeedCommentSelDto.builder()
                                                                            .ifeed(vo.getIfeed())
                                                                            .startIdx(0)
                                                                            .rowCount(2)
                                                                            .build());
            if(comments.size() == 4) {
                vo.setIsMoreComment(1);
                comments.remove(comments.size() - 1);
            }
            vo.setComments(comments);
        }
        return list;
    }
    // ----------- FeedFav
    public ResVo toggleFeedFav(FeedFavDto dto){
        // 딜리트 먼저
        int affectedRow = feedFavMapper.delFeedFav(dto);
        if( affectedRow == 1 ){
            return new ResVo(0);
        }
        feedFavMapper.insFeedFav(dto);
        return new ResVo(1);
    }
    // -------------- FeedComment
    public ResVo insFeedComment(FeedCommentInsDto dto){
        try {
            int affectedRow = feedCommnetMapper.insFeedComment(dto); // 0, 1
            return new ResVo(affectedRow);
        }catch (Exception e){
            return new ResVo(0);
        }
    }
}
