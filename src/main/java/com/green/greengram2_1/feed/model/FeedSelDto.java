package com.green.greengram2_1.feed.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FeedSelDto {
    private int loginedIuser;
    private int targetIuser;

    private int startIdx;
    private int rowCount;
}
