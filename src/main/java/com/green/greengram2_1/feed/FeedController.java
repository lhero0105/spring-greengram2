package com.green.greengram2_1.feed;

import com.green.greengram2_1.ResVo;
import com.green.greengram2_1.feed.model.FeedInsDto;
import com.green.greengram2_1.feed.model.FeedSelDto;
import com.green.greengram2_1.feed.model.FeedSelVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
@Tag(name = "피드 API", description = "피드 관련 처리")
@Slf4j

public class FeedController {
    private final FeedService service;

    @Operation(summary = "피드추가", description = "피드 추가 처리")
    @Parameters(value = {
              @Parameter(name = "iuser", description = "유저pk")
            , @Parameter(name = "contents", description = "내용")
            , @Parameter(name = "location", description = "장소")
            , @Parameter(name = "pics", description = "사진들")
    })
    @PostMapping
    public ResVo postFeedDto(@RequestBody FeedInsDto dto){
        return service.InsFeedDto(dto);
    }
    // 전체피드, 해당유저 피드 합쳤습니다.
    @GetMapping
    public List<FeedSelVo> getFeedAll(int page, int loginedIuser
            , @RequestParam(defaultValue = "0", required = false) int targetIuser){
        // @RequestParam 옵션입니다.
        // loginedIuse 내 pk를 보낸 이유는 좋아요 기능을 위함 입니다.
        // targetIuser 이 사람이 작성한 피드를 보고싶기에
        log.info("targetIuser : {}", targetIuser);
        final int ROW_COUNT = 30; // 추 후에 이부분을 외부파일에서 수정하도록 합니다.

        return service.getFeedAll(FeedSelDto.builder()
                        .loginedIuser(loginedIuser)
                        .targetIuser(targetIuser)
                        .rowCount(ROW_COUNT)
                        .startIdx((page-1) * ROW_COUNT)
                        .build());
    }
}
