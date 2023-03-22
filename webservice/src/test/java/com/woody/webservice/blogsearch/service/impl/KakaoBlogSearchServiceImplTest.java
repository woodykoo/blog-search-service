package com.woody.webservice.blogsearch.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.woody.client.kakao.KakaoApiClient;
import com.woody.client.kakao.dto.response.KakaoBlogSearchResponse;
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
class KakaoBlogSearchServiceImplTest {

    @Mock
    private KakaoApiClient kakaoApiClient;

    @InjectMocks
    private KakaoBlogSearchServiceImpl kakaoBlogSearchService;

    @Test
    @DisplayName("카카오 블로그 조회 테스트")
    void searchBlog() throws Exception {
        // given
        BlogSearchConditionData searchConditionData = new BlogSearchConditionData("테스트", 1, 10, Sort.ACCURACY);
        KakaoBlogSearchResponse searchResponse = getKakaoBlogSearchResponse();

        Mockito.when(kakaoApiClient.searchBlogs(anyString(), anyString(), anyInt(), anyInt()))
                .thenReturn(Mono.just(searchResponse));

        // when
        Mono<BlogSearchResultData> blogSearchResultData = kakaoBlogSearchService.searchBlogs(searchConditionData);

        // then
        StepVerifier.create(blogSearchResultData)
                .assertNext(result -> {
                    assertEquals(1, result.getCurrentPage());
                    assertEquals(10, result.getSize());
                    assertEquals(1, result.getDocuments().size());
                })
                .expectComplete()
                .verify();
    }

    private KakaoBlogSearchResponse getKakaoBlogSearchResponse() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String responseJson = "{\n" +
                "    \"documents\": [\n" +
                "        {\n" +
                "            \"blogname\": \"sfdg345\",\n" +
                "            \"contents\": \"<b>asdgdf</b>\",\n" +
                "            \"datetime\": \"2018-12-08T13:20:25.000+09:00\",\n" +
                "            \"thumbnail\": \"\",\n" +
                "            \"title\": \"asdf\",\n" +
                "            \"url\": \"http://asdtg35.tistory.com/1\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"meta\": {\n" +
                "        \"is_end\": true,\n" +
                "        \"pageable_count\": 1,\n" +
                "        \"total_count\": 2\n" +
                "    }\n" +
                "}";

        return objectMapper.readValue(responseJson, KakaoBlogSearchResponse.class);
    }
}