package com.woody.domain.blog.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.woody.domain.blog.entity.BlogSearchStatistics;
import com.woody.domain.blog.entity.QBlogSearchStatistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by woody 2023.03.19
 * 블로그 검색 통계 조회 Repository
 * */
@Repository
@RequiredArgsConstructor
public class BlogSearchStatisticsQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private static final QBlogSearchStatistics BLOG_SEARCH_STATISTICS = QBlogSearchStatistics.blogSearchStatistics;

    public List<BlogSearchStatistics> findPopularSearches() {
        return jpaQueryFactory.selectFrom(BLOG_SEARCH_STATISTICS)
                .orderBy(BLOG_SEARCH_STATISTICS.count.desc())
                .limit(10)
                .fetch();
    }
}
