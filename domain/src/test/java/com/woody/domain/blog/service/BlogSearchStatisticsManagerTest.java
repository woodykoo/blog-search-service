package com.woody.domain.blog.service;

import com.woody.domain.blog.entity.BlogSearchStatistics;
import com.woody.domain.blog.repository.BlogSearchStatisticsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class BlogSearchStatisticsManagerTest {

    @InjectMocks
    private BlogSearchStatisticsManager blogSearchStatisticsManager;

    @Mock
    private BlogSearchStatisticsRepository blogSearchStatisticsRepository;

    @Test
    @DisplayName("검색어 조회수 증가 테스트")
    void increaseCount() throws Exception {
        // given
        String keyword = "테스트";
        int searchCount = 10;
        BlogSearchStatistics blogSearchStatistics = new BlogSearchStatistics(keyword);

        // when
        Mockito.when(blogSearchStatisticsRepository.findByKeyword(keyword))
                .thenReturn(Optional.of(blogSearchStatistics));

        for (int i=0; i<searchCount; i++) {
            blogSearchStatisticsManager.increaseCount(keyword);
        }

        // then
        assertThat(blogSearchStatistics.getCount()).isEqualTo(searchCount);
        assertThat(blogSearchStatistics.getKeyword()).isEqualTo(keyword);
    }
}