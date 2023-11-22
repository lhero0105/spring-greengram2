package com.green.greengram2_1.feed;

import com.green.greengram2_1.feed.model.FeedInsProcDto;
import com.green.greengram2_1.feed.model.FeedSelDto;
import com.green.greengram2_1.feed.model.FeedSelVo;
import com.green.greengram2_1.user.model.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedMapper {
    int insFeed(FeedInsProcDto dto);

    List<FeedSelVo> selFeedAll(FeedSelDto dto);
}
