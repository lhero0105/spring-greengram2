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

    // n+1 이슈 허용
    public List<FeedSelVo> getFeedAll(FeedSelDto dto){
        List<FeedSelVo> list = mapper.selFeedAll(dto);
        for ( FeedSelVo vo : list ) {
            List<String> pics = picsMapper.selFeedPicsAll(vo.getIfeed());
            vo.setPics(pics);

            List<FeedCommentSelVo> comments = feedCommnetMapper.selCommentAll(
                    FeedCommentSelDto.builder()
                            .ifeed(vo.getIfeed())
                            .startIdx(0)
                            .rowCount(4)
                            .build());
            if(comments.size() == 4){
                vo.setIsMoreComment(1);
                comments.remove(comments.size() -1 );
            }
            vo.setComments(comments);
        }
        return list;
    }

    public ResVo delFeed(FeedDelDto dto){
        // 1. iuser가 쓴 feed가 맞는지 확인한다.
        Integer ifeed = mapper.selFeedMine(dto);
        if(ifeed == null){
            return new ResVo(0);
        }

        // 3. 만약 쓴 글이다. 해당하는 feed에 달린
        // 댓글, 좋아요, 사진들을 먼저 삭제한다.
        int affectedRowComment = feedCommnetMapper.delCommentForFeed(dto);
        int affectedRowFav = feedFavMapper.delFeedFavForFeed(dto);
        int affectedRowPics = picsMapper.delFeedpicsForFeed(dto);
        // 4. feed 삭제처리 > ResVo(1) 리턴
        int affectedRowFeed = mapper.delFeed(dto);
        return new ResVo(1);
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
            FeedCommentInsProcDto pDto = new FeedCommentInsProcDto(dto);
            int affectedRow = feedCommnetMapper.insFeedComment(pDto); // 0, 1
            log.info("affectedRow : {}", affectedRow);
            return new ResVo(pDto.getIfeedComment());
        }catch (Exception e){
            return new ResVo(0);
        }
    }

    public List<FeedCommentSelVo> getCommentAll(int ifeed){
        return feedCommnetMapper.selCommentAll(
                FeedCommentSelDto.builder()
                        .ifeed(ifeed)
                        .startIdx(4)
                        .rowCount(9999)
                        .build());
    }
    public ResVo delComment(FeedCommentDelDto dto){
        int affectedRow = feedCommnetMapper.delComment(dto);
        return new ResVo(affectedRow);
    }
}
