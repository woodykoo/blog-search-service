package com.woody.webservice.blogsearch.api;

import com.woody.client.kakao.exception.KakaoErrorResponse;
import com.woody.client.kakao.exception.KakaoServerErrorException;
import com.woody.client.naver.exception.NaverErrorResponse;
import com.woody.client.naver.exception.NaverServerErrorException;
import com.woody.webservice.blogsearch.api.response.BlogSearchResponse;
import com.woody.webservice.blogsearch.service.BlogSearchService;
import com.woody.webservice.blogsearch.service.data.BlogSearchResultData;
import com.woody.webservice.exception.ErrorCode;
import com.woody.webservice.exception.ErrorResponse;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

@WebFluxTest(BlogSearchApi.class)
class BlogSearchApiTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean(name = "KakaoBlogSearch")
    private BlogSearchService kakaoBlogSearchService;

    @MockBean(name = "NaverBlogSearch")
    private BlogSearchService naverBlogSearchService;

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class 블로그_조회_테스트 {

        @Nested
        class 정상_CASE {

            private BlogSearchResultData blogSearchResultData;

            @BeforeEach
            void setUp() {
                List<BlogSearchResultData.Document> documents = new ArrayList<>();
                for (int i=0; i<5; i++) {
                    documents.add(new BlogSearchResultData.Document("title" + i, "contents" + i, "url" + i, "blogname" + i, "thumbnail" + i, LocalDateTime.now()));
                }

                this.blogSearchResultData = BlogSearchResultData.builder()
                        .documents(documents)
                        .currentPage(1)
                        .totalCount(5)
                        .pageableCount(5)
                        .size(10)
                        .first(true)
                        .last(true)
                        .build();
            }

            @Test
            void 카카오_블로그_조회_성공() throws Exception {
                // given
                Mockito.when(kakaoBlogSearchService.searchBlog(any()))
                        .thenReturn(blogSearchResultData);

                // when
                BlogSearchResponse responseBody = webTestClient.get().uri(uriBuilder ->
                                uriBuilder.path("/blog/search")
                                        .queryParam("keyword", "테스트")
                                        .queryParam("page", 1)
                                        .queryParam("size", 10)
                                        .build())
                        .exchange()
                        .expectStatus()
                        .isOk()
                        .expectBody(BlogSearchResponse.class)
                        .returnResult()
                        .getResponseBody();

                // then
                assertThat(responseBody).isNotNull();
                assertThat(responseBody.getCurrentPage()).isEqualTo(1);
                assertThat(responseBody.getTotalCount()).isEqualTo(5);
                assertThat(responseBody.getDocuments().size()).isEqualTo(5);
            }

            @Test
            void 카카오_서버_장애_네이버_블로그_조회_성공() throws Exception {
                // given
                Mockito.when(kakaoBlogSearchService.searchBlog(any()))
                        .thenThrow(new KakaoServerErrorException(new KakaoErrorResponse("ServerError", "ServerError")));

                Mockito.when(naverBlogSearchService.searchBlog(any()))
                        .thenReturn(blogSearchResultData);

                // when
                BlogSearchResponse responseBody = webTestClient.get().uri(uriBuilder ->
                                uriBuilder.path("/blog/search")
                                        .queryParam("keyword", "테스트")
                                        .queryParam("page", 1)
                                        .queryParam("size", 10)
                                        .build())
                        .exchange()
                        .expectStatus()
                        .isOk()
                        .expectBody(BlogSearchResponse.class)
                        .returnResult()
                        .getResponseBody();

                // then
                assertThat(responseBody).isNotNull();
                assertThat(responseBody.getCurrentPage()).isEqualTo(1);
                assertThat(responseBody.getTotalCount()).isEqualTo(5);
                assertThat(responseBody.getDocuments().size()).isEqualTo(5);
            }
        }

        @Nested
        class 잘못된_파마리터_전달_CASE {

            @Test
            void 검색_키워드_누락_요청() {
                // when
                ErrorResponse responseBody = webTestClient.get().uri(uriBuilder ->
                                uriBuilder.path("/blog/search")
                                        .queryParam("page", 51)
                                        .queryParam("size", 10)
                                        .build())
                        .exchange()
                        .expectStatus()
                        .isBadRequest()
                        .expectBody(ErrorResponse.class)
                        .returnResult()
                        .getResponseBody();

                // then
                assertThat(responseBody).isNotNull();
                assertThat(responseBody.getErrorCode()).isEqualTo(ErrorCode.BAD_REQUEST.getCode());
            }

            @Test
            void 요청_페이지_50_초과_요청() {

                // when
                ErrorResponse responseBody = webTestClient.get().uri(uriBuilder ->
                                uriBuilder.path("/blog/search")
                                        .queryParam("keyword", "테스트")
                                        .queryParam("page", 51)
                                        .queryParam("size", 10)
                                        .build())
                        .exchange()
                        .expectStatus()
                        .isBadRequest()
                        .expectBody(ErrorResponse.class)
                        .returnResult()
                        .getResponseBody();

                // then
                assertThat(responseBody).isNotNull();
                assertThat(responseBody.getErrorCode()).isEqualTo(ErrorCode.BAD_REQUEST.getCode());
            }

            @Test
            void 요청_사이즈_50_초과_요청() {

                // when
                ErrorResponse responseBody = webTestClient.get().uri(uriBuilder ->
                                uriBuilder.path("/blog/search")
                                        .queryParam("keyword", "테스트")
                                        .queryParam("page", 1)
                                        .queryParam("size", 51)
                                        .build())
                        .exchange()
                        .expectStatus()
                        .isBadRequest()
                        .expectBody(ErrorResponse.class)
                        .returnResult()
                        .getResponseBody();

                // then
                assertThat(responseBody).isNotNull();
                assertThat(responseBody.getErrorCode()).isEqualTo(ErrorCode.BAD_REQUEST.getCode());
            }
        }

        @Nested
        class 서비스_장애_CASE {
            @Test
            void 카카오_네이버_서버장애_조회실패() {
                // given
                Mockito.when(kakaoBlogSearchService.searchBlog(any()))
                        .thenThrow(new KakaoServerErrorException(new KakaoErrorResponse()));

                Mockito.when(naverBlogSearchService.searchBlog(any()))
                        .thenThrow(new NaverServerErrorException(new NaverErrorResponse("SE99", "서버 내부에 오류가 발생했습니다.")));

                // when
                ErrorResponse responseBody = webTestClient.get().uri(uriBuilder ->
                                uriBuilder.path("/blog/search")
                                        .queryParam("keyword", "테스트")
                                        .queryParam("page", 1)
                                        .queryParam("size", 10)
                                        .build())
                        .exchange()
                        .expectStatus()
                        .is5xxServerError()
                        .expectBody(ErrorResponse.class)
                        .returnResult()
                        .getResponseBody();

                // then
                assertThat(responseBody).isNotNull();
                assertThat(responseBody.getErrorCode()).isEqualTo(ErrorCode.INTERNAL_SERVER_ERROR.getCode());
            }
        }
    }
}