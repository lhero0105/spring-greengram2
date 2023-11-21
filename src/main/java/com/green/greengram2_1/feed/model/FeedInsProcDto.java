package com.green.greengram2_1.feed.model;

import lombok.Data;

import java.util.List;

@Data
public class FeedInsProcDto {
    private int ifeed;
    private int iuser;
    private String contents;
    private String location;
    private List<String> pics;
    public FeedInsProcDto(FeedInsDto dto){
        iuser = dto.getIuser();
        contents = dto.getContents();
        location = dto.getLocation();
        pics = dto.getPics();
    }
}
