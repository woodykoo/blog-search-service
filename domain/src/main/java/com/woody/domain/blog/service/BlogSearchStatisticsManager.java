package com.woody.domain.blog.service;

import com.woody.domain.aop.annotaion.RedissonLock;
import com.woody.domain.blog.entity.BlogSearchStatistics;
import com.woody.domain.blog.repository.BlogSearchStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by woody 2023.03.18
 * 블로그 검색 통계 정보를 생성,수정,삭제를 관리하는 서비스
 * */
@Service
@Transactional
@RequiredArgsConstructor
public class BlogSearchStatisticsManager {

    private final BlogSearchStatisticsRepository blogSearchStatisticsRepository;

    @RedissonLock(prefix = "blogSearch", key = "keyword")
    public void increaseCount(String keyword) {
        BlogSearchStatistics blogSearchStatistics = blogSearchStatisticsRepository.findByKeyword(keyword)
                .orElseGet(() -> saveKeyword(keyword));
        blogSearchStatistics.increaseCount();
    }

    private BlogSearchStatistics saveKeyword(String keyword) {
        return blogSearchStatisticsRepository.save(new BlogSearchStatistics(keyword));
    }
}
