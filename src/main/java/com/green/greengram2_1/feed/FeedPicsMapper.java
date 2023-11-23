package com.green.greengram2_1.feed;

import com.green.greengram2_1.feed.model.FeedDelDto;
import com.green.greengram2_1.feed.model.FeedInsProcDto;
import com.green.greengram2_1.feed.model.FeedSelVo;
import com.green.greengram2_1.user.model.UserPatchPicDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedPicsMapper {
    List<String> selFeedPicsAll(int ifeed);
    int insFeedPic(FeedInsProcDto dto);
    int updFeedpic(UserPatchPicDto dto);
    int delFeedpicsForFeed(FeedDelDto dto);
}
