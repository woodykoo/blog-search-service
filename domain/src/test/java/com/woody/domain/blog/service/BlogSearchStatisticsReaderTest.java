package com.woody.domain.blog.service;

import com.woody.domain.blog.entity.BlogSearchStatistics;
import com.woody.domain.blog.repository.BlogSearchStatisticsQueryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BlogSearchStatisticsReaderTest {

    @InjectMocks
    private BlogSearchStatisticsReader blogSearchStatisticsReader;

    @Mock
    private BlogSearchStatisticsQueryRepository blogSearchStatisticsQueryRepository;

    @Test
    @DisplayName("인기 검색어 조회 테스트")
    void getPopularSearches() throws Exception {
        // given
        List<BlogSearchStatistics> blogSearchStatisticsList = new ArrayList<>();
        for (int i=0; i<10; i++) {
            BlogSearchStatistics blogSearchStatistics = new BlogSearchStatistics("테스트" + i);

            blogSearchStatistics.increaseCount();

            blogSearchStatisticsList.add(blogSearchStatistics);
        }

        // when
        Mockito.when(blogSearchStatisticsQueryRepository.findPopularSearches())
                .thenReturn(blogSearchStatisticsList);

        blogSearchStatisticsReader.getPopularSearches();
        // then
    }
}