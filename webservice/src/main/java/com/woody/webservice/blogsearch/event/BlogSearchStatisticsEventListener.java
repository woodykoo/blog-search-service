package com.woody.webservice.blogsearch.event;

import com.woody.domain.blog.service.BlogSearchStatisticsManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by woody 2023.03.20
 * 블로그 검색 통계 이벤트 리스너
 * */
@Slf4j
@Component
@RequiredArgsConstructor
public class BlogSearchStatisticsEventListener {

    private final BlogSearchStatisticsManager blogSearchStatisticsManager;

    /**
     * 블로그 통계 이벤트를 받아서 검색 회수를 증가시키는 이벤트 리스너
     * @param event BlogSearchStatisticsEvent
     * */
    @Async
    @EventListener
    public void blogSearch(BlogSearchStatisticsEvent event) {
        blogSearchStatisticsManager.increaseCount(event.getKeyword());
    }
}
