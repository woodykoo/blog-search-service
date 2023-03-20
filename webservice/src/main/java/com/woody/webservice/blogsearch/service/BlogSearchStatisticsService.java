package com.woody.webservice.blogsearch.service;

import com.woody.webservice.blogsearch.service.data.BlogPopularSearchData;

import java.util.List;

/**
 * Created by woody 2023.03.20
 * 블로그 검색 통계 조회 서비스
 * */
public interface BlogSearchStatisticsService {

    /**
     * 인기 검색어 목록 조회
     * @return List<BlogPopularSearchData>
     * */
    List<BlogPopularSearchData> getPopularSearches();
}
