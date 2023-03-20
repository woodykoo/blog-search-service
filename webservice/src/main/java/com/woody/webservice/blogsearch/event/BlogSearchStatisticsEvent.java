package com.woody.webservice.blogsearch.event;

/**
 * Created by woody 2023.03.20
 * 블로그 검색 통계 이벤트
 * */
public class BlogSearchStatisticsEvent {

    private final String keyword;

    public BlogSearchStatisticsEvent(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return this.keyword;
    }
}
