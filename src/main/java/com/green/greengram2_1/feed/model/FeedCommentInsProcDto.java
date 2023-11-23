package com.green.greengram2_1.feed.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedCommentInsProcDto {
    private int ifeedComment;
    private int ifeed;
    private int iuser;
    private String comment;

    public FeedCommentInsProcDto(FeedCommentInsDto dto) {
        this.ifeed = dto.getIfeed();
        this.iuser = dto.getIuser();
        this.comment = dto.getComment();
    }
}
