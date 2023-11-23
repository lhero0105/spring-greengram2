package com.green.greengram2_1.feed;

import com.green.greengram2_1.feed.model.FeedDelDto;
import com.green.greengram2_1.feed.model.FeedFavDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedFavMapper {
    int delFeedFav(FeedFavDto dto);
    int insFeedFav(FeedFavDto dto);
    int delFeedFavForFeed(FeedDelDto dto);
}
