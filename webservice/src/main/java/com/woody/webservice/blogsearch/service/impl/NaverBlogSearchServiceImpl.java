package com.woody.webservice.blogsearch.service.impl;

import com.woody.client.naver.NaverBlogSearchClient;
import com.woody.client.naver.dto.response.NaverBlogSearchResponse;
import com.woody.webservice.blogsearch.enums.Sort;
import com.woody.webservice.blogsearch.event.BlogSearchStatisticsEvent;
import com.woody.webservice.blogsearch.service.BlogSearchService;
import com.woody.webservice.blogsearch.service.data.BlogSearchConditionData;
import com.woody.webservice.blogsearch.service.data.BlogSearchResultData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@Service("NaverBlogSearch")
@RequiredArgsConstructor
public class NaverBlogSearchServiceImpl implements BlogSearchService {

    private final NaverBlogSearchClient naverBlogSearchClient;

    private final ApplicationEventPublisher publisher;

    @Override
    public BlogSearchResultData searchBlog(BlogSearchConditionData searchCondition) {

        NaverBlogSearchResponse searchResponse = naverBlogSearchClient.searchBlog(searchCondition.getKeyword(), searchCondition.getSize(), searchCondition.getPage(), getSort(searchCondition.getSort()));

        publisher.publishEvent(new BlogSearchStatisticsEvent(searchCondition.getKeyword()));

        return mapBlogSearchResult(searchResponse);
    }

    private BlogSearchResultData mapBlogSearchResult(NaverBlogSearchResponse searchResponse) {
        int pageableCount = getPageableCount(searchResponse.getTotal(), searchResponse.getDisplay());
        int totalPage = pageableCount/searchResponse.getDisplay();

        return BlogSearchResultData.builder()
                .currentPage(searchResponse.getStart())
                .totalCount(searchResponse.getTotal())
                .pageableCount(pageableCount)
                .size(searchResponse.getDisplay())
                .first(searchResponse.getStart() == 1)
                .last(searchResponse.getStart() == totalPage)
                .documents(searchResponse.getItems().stream().map(item -> new BlogSearchResultData.Document(item.getTitle(), item.getDescription(), item.getLink(), item.getBloggername(), "", item.getPostdate().atStartOfDay())).collect(Collectors.toList()))
                .build();
    }

    private int getPageableCount(int totalCount, int display) {
        // 카카오가 50페이지 제한
        int maxPage = 50;

        int maxCount = display * maxPage;

        return Math.min(totalCount, maxCount);
    }

    private String getSort(Sort sort) {
        switch (sort) {
            case ACCURACY:
                return "sim";
            case RECENCY:
                return "date";
            default:
                return "sim";
        }
    }
}
