package com.green.greengram2_1.feed;

import com.green.greengram2_1.feed.model.FeedCommentInsDto;
import com.green.greengram2_1.feed.model.FeedCommentSelDto;
import com.green.greengram2_1.feed.model.FeedCommentSelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedCommentMapper {
    int insFeedComment(FeedCommentInsDto dto);
    List<FeedCommentSelVo> selCommentAll(FeedCommentSelDto dto);
}
