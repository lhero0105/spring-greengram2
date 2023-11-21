package com.green.greengram2_1.feed.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class FeedInsDto {
    private int iuser;
    private String contents;
    private String location;
    private List<String> pics;
}
