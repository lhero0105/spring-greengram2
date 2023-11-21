package com.green.greengram2_1.feed;

import com.green.greengram2_1.feed.model.FeedSelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedPicsMapper {
    List<String> selFeedPicsAll(int ifeed);
}
