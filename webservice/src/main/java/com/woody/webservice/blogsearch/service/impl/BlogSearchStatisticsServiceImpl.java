package com.woody.webservice.blogsearch.service.impl;

import com.woody.webservice.blogsearch.service.BlogSearchStatisticsService;
import com.woody.webservice.blogsearch.service.data.BlogPopularSearchData;
import com.woody.domain.blog.entity.BlogSearchStatistics;
import com.woody.domain.blog.service.BlogSearchStatisticsReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by woody 2023.03.20
 * 블로그 검색 통계 조회 서비스 구현체
 * */
@Service
@RequiredArgsConstructor
public class BlogSearchStatisticsServiceImpl implements BlogSearchStatisticsService {

    private final BlogSearchStatisticsReader blogSearchStatisticsReader;

    @Override
    public List<BlogPopularSearchData> getPopularSearches() {
        List<BlogSearchStatistics> popularSearches = blogSearchStatisticsReader.getPopularSearches();

        List<BlogPopularSearchData> blogPopularSearchData = new ArrayList<>();

        for (int i=0; i<popularSearches.size(); i++) {
            BlogSearchStatistics blogSearchStatistics = popularSearches.get(i);
            blogPopularSearchData.add(new BlogPopularSearchData(i+1, blogSearchStatistics.getKeyword(), blogSearchStatistics.getCount()));
        }

        return blogPopularSearchData;
    }
}
