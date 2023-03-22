package com.woody.webservice.blogsearch.service.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by woody 2023.03.22
 * 블로그 검색 결과 DTO
 * */
@Getter
@Builder
public class BlogSearchResultData {

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
}
