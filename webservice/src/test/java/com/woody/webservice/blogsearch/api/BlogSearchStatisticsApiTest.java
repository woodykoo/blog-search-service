package com.woody.webservice.blogsearch.api;

import com.woody.webservice.blogsearch.api.response.BlogPopularSearchesResponse;
import com.woody.webservice.blogsearch.service.BlogSearchStatisticsService;
import com.woody.webservice.blogsearch.service.data.BlogPopularSearchData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@WebFluxTest(BlogSearchStatisticsApi.class)
class BlogSearchStatisticsApiTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private BlogSearchStatisticsService blogSearchStatisticsService;

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class 블로그_검색_통계_조회_테스트 {
        @Nested
        class 정상_조회_CASE {

            private List<BlogPopularSearchData> blogPopularSearchDataList;

            @BeforeEach
            void setUp() {
                this.blogPopularSearchDataList = new ArrayList<>();
                int count = 100;

                for (int i=0; i<10; i++) {
                    this.blogPopularSearchDataList.add(new BlogPopularSearchData(i+1, "테스트" + i, count-i));
                }
            }
            @Test
            void 정상_조회() {
                // given
                Mockito.when(blogSearchStatisticsService.getPopularSearches())
                        .thenReturn(blogPopularSearchDataList);

                // when
                BlogPopularSearchesResponse responseBody = webTestClient.get()
                        .uri("/blog/statistics/popular/searches")
                        .exchange()
                        .expectStatus()
                        .isOk()
                        .expectBody(BlogPopularSearchesResponse.class)
                        .returnResult()
                        .getResponseBody();

                // then
                assertThat(responseBody).isNotNull();
                assertThat(responseBody.getBlogPopularSearches()).isNotNull();
                assertThat(responseBody.getBlogPopularSearches().size()).isEqualTo(10);
            }
        }

    }
}