package com.green.greengram2_1.feed;

import com.green.greengram2_1.feed.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedCommentMapper {
    int insFeedComment(FeedCommentInsProcDto dto);
    List<FeedCommentSelVo> selCommentAll(FeedCommentSelDto dto);
    int delComment(FeedCommentDelDto dto);
    int delCommentForFeed(FeedDelDto dto);
}
