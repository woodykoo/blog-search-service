package com.woody.webservice.blogsearch.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.woody.client.naver.NaverApiClient;
import com.woody.client.naver.dto.response.NaverBlogSearchResponse;
import com.woody.webservice.blogsearch.enums.Sort;
import com.woody.webservice.blogsearch.service.data.BlogSearchConditionData;
import com.woody.webservice.blogsearch.service.data.BlogSearchResultData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class NaverBlogSearchServiceImplTest {

    @Mock
    private NaverApiClient naverApiClient;

    @InjectMocks
    private NaverBlogSearchServiceImpl naverBlogSearchService;

    @Test
    @DisplayName("네이버 블로그 조회 테스트")
    void searchBlog() throws Exception {
        // given
        BlogSearchConditionData searchConditionData = new BlogSearchConditionData("테스트", 1, 10, Sort.ACCURACY);
        NaverBlogSearchResponse searchResponse = getNaverBlogSearchResponse();

        Mockito.when(naverApiClient.searchBlogs(anyString(), anyString(), anyInt(), anyInt()))
                .thenReturn(Mono.just(searchResponse));

        // when
        Mono<BlogSearchResultData> blogSearchResultData = naverBlogSearchService.searchBlogs(searchConditionData);

        // then
        StepVerifier.create(blogSearchResultData)
                .assertNext(result -> {
                    assertEquals(1, result.getCurrentPage());
                    assertEquals(2, result.getSize());
                    assertEquals(2, result.getDocuments().size());
                })
                .expectComplete()
                .verify();
    }

    private NaverBlogSearchResponse getNaverBlogSearchResponse() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String responseJson = "{\n" +
                "    \"total\": 2,\n" +
                "    \"start\": 1,\n" +
                "    \"display\": 2,\n" +
                "    \"items\": [\n" +
                "        {\n" +
                "            \"title\": \"<b>asdgdf</b>\",\n" +
                "            \"link\": \"https://blog.naver.com/sak64852/80071173752\",\n" +
                "            \"description\": \"\",\n" +
                "            \"bloggername\": \"sak64852님의 블로그\",\n" +
                "            \"bloggerlink\": \"blog.naver.com/sak64852\",\n" +
                "            \"postdate\": \"20060821\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"title\": \"gsdfg\",\n" +
                "            \"link\": \"https://blog.naver.com/gurwhd/34627458\",\n" +
                "            \"description\": \"<b>asdgdf</b>\",\n" +
                "            \"bloggername\": \"gurwhd님의 블로그\",\n" +
                "            \"bloggerlink\": \"blog.naver.com/gurwhd\",\n" +
                "            \"postdate\": \"20070220\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";


        return objectMapper.readValue(responseJson, NaverBlogSearchResponse.class);
    }

}