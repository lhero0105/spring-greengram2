package com.green.greengram2_1.feed.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FeedCommentDelDto {
    private int ifeedComment;
    private int loginedIuser;
}
