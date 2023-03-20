package com.woody.webservice.blogsearch.api.response;

import com.woody.webservice.blogsearch.service.data.BlogSearchResultData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by woody 2023.03.20
 * 블로그 검색 응답 DTO
 * */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogSearchResponse {

    private List<Document> documents;

    private int currentPage;

    private int totalCount;

    private int pageableCount;

    private int size;

    private boolean first;

    private boolean last;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Document {
        private String title;
        private String contents;
        private String url;
        private String blogname;
        private String thumbnail;
        private LocalDateTime datetime;
    }

    public static BlogSearchResponse from(BlogSearchResultData blogSearchResultData) {
        return BlogSearchResponse.builder()
                .documents(blogSearchResultData.getDocuments().stream().map(document -> new Document(document.getTitle(), document.getContents(), document.getUrl(), document.getBlogname(), document.getThumbnail(), document.getDatetime())).collect(Collectors.toList()))
                .currentPage(blogSearchResultData.getCurrentPage())
                .totalCount(blogSearchResultData.getTotalCount())
                .pageableCount(blogSearchResultData.getPageableCount())
                .size(blogSearchResultData.getSize())
                .first(blogSearchResultData.isFirst())
                .last(blogSearchResultData.isLast())
                .build();
    }
}
