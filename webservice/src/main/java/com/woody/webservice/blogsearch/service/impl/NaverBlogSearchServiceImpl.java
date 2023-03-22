package com.woody.webservice.blogsearch.service.impl;

import com.woody.client.naver.NaverApiClient;
import com.woody.client.naver.dto.response.NaverBlogSearchResponse;
import com.woody.webservice.blogsearch.enums.Sort;
import com.woody.webservice.blogsearch.service.BlogSearchService;
import com.woody.webservice.blogsearch.service.data.BlogSearchConditionData;
import com.woody.webservice.blogsearch.service.data.BlogSearchResultData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NaverBlogSearchServiceImpl implements BlogSearchService {

    private final NaverApiClient naverApiClient;

    @Override
    public Mono<BlogSearchResultData> searchBlogs(BlogSearchConditionData searchCondition) {
        return naverApiClient.searchBlogs(searchCondition.getKeyword(), getSort(searchCondition.getSort()), searchCondition.getPage(), searchCondition.getSize())
                .map(this::mapBlogSearchResult);
    }

    private BlogSearchResultData mapBlogSearchResult(NaverBlogSearchResponse searchResponse) {
        int pageableCount = getPageableCount(searchResponse.getTotal(), searchResponse.getDisplay());
        int totalPage = (int) Math.ceil((double) pageableCount/searchResponse.getDisplay());

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
