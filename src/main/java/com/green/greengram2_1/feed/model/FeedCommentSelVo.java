package com.green.greengram2_1.feed.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedCommentSelVo {
    private int ifeedComment;
    private String comment;
    private int writerIuser;
    private String createdAt;
    private String writerNm;
    private String writerPic;
}
