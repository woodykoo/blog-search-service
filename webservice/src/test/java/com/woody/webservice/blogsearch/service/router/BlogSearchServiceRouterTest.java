package com.woody.webservice.blogsearch.service.router;

import com.woody.webservice.blogsearch.enums.BlogSearchSource;
import com.woody.webservice.blogsearch.service.BlogSearchService;
import com.woody.webservice.blogsearch.service.impl.KakaoBlogSearchServiceImpl;
import com.woody.webservice.blogsearch.service.impl.NaverBlogSearchServiceImpl;
import com.woody.webservice.exception.NotFoundServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class BlogSearchServiceRouterTest {
    @InjectMocks
    private BlogSearchServiceRouter blogSearchServiceRouter;

    @Mock
    private KakaoBlogSearchServiceImpl kakaoBlogSearchService;

    @Mock
    private NaverBlogSearchServiceImpl naverBlogSearchService;

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class 블로그_검색_서비스_라우터_테스트 {

        @Nested
        class 정상_서비스_조회_CASE {

            @BeforeEach
            void setUp() {
                Map<String, BlogSearchService> blogSearchServiceMap = Map.of(BlogSearchSource.KAKAO.getServiceName(), kakaoBlogSearchService, BlogSearchSource.NAVER.getServiceName(), naverBlogSearchService);
                blogSearchServiceRouter = new BlogSearchServiceRouter(blogSearchServiceMap);
            }

            @Test
            void 카카오_서비스_조회() {
                // when
                BlogSearchService blogSearchService = blogSearchServiceRouter.getServiceBySource(BlogSearchSource.KAKAO);

                // then
                assertThat(blogSearchService).isInstanceOf(KakaoBlogSearchServiceImpl.class);
            }

            @Test
            void 네이버_서비스_조회() {
                // when
                BlogSearchService blogSearchService = blogSearchServiceRouter.getServiceBySource(BlogSearchSource.NAVER);

                // then
                assertThat(blogSearchService).isInstanceOf(NaverBlogSearchServiceImpl.class);
            }
        }

        @Nested
        class 네이버_서비스가_없는_CASE {
            private BlogSearchServiceRouter blogSearchServiceRouter;

            @BeforeEach
            void setUp() {
                Map<String, BlogSearchService> blogSearchServiceMap = Map.of(BlogSearchSource.KAKAO.getServiceName(), kakaoBlogSearchService);
                blogSearchServiceRouter = new BlogSearchServiceRouter(blogSearchServiceMap);
            }

            @Test
            void 네이버_서비스_조회_실패() {
                // when
                assertThrows(NotFoundServiceException.class,
                        () -> blogSearchServiceRouter.getServiceBySource(BlogSearchSource.NAVER));
            }
        }
    }
}