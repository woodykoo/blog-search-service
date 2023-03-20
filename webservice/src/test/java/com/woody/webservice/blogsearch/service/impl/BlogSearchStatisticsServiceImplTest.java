package com.woody.webservice.blogsearch.service.impl;

import com.woody.domain.blog.entity.BlogSearchStatistics;
import com.woody.domain.blog.service.BlogSearchStatisticsReader;
import com.woody.webservice.blogsearch.service.data.BlogPopularSearchData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class BlogSearchStatisticsServiceImplTest {

    @InjectMocks
    private BlogSearchStatisticsServiceImpl blogSearchStatisticsService;

    @Mock
    private BlogSearchStatisticsReader blogSearchStatisticsReader;


    @Test
    @DisplayName("인기 검색어 목록 조회")
    void getPopularSearches() throws Exception {
        // given
        List<BlogSearchStatistics> blogSearchStatisticsList  = new ArrayList<>();
        int count = 20;
        for (int i=0; i<10; i++) {
            blogSearchStatisticsList.add(new BlogSearchStatistics((long) i, "테스트" + i, count-i, LocalDateTime.now(), LocalDateTime.now()));
        }

        // when
        Mockito.when(blogSearchStatisticsReader.getPopularSearches())
                .thenReturn(blogSearchStatisticsList);

        List<BlogPopularSearchData> blogPopularSearchDataList = blogSearchStatisticsService.getPopularSearches();

        // then
        assertThat(blogPopularSearchDataList).isNotNull();
        assertThat(blogPopularSearchDataList.size()).isEqualTo(10);
        assertThat(blogPopularSearchDataList.get(0).getCount()).isEqualTo(20);
        assertThat(blogPopularSearchDataList.get(0).getRank()).isEqualTo(1);
        assertThat(blogPopularSearchDataList.get(9).getRank()).isEqualTo(10);
    }

}