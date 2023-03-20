package com.woody.domain.blog.service;

import com.woody.domain.blog.entity.BlogSearchStatistics;
import com.woody.domain.blog.repository.BlogSearchStatisticsQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by woody 2023.03.18
 * 블로그 검색 통계 정보를 조회하는 서비스
 * */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BlogSearchStatisticsReader {

    private final BlogSearchStatisticsQueryRepository blogSearchStatisticsQueryRepository;

    public List<BlogSearchStatistics> getPopularSearches() {
        return blogSearchStatisticsQueryRepository.findPopularSearches();
    }
}
