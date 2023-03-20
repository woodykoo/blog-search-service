package com.woody.webservice.blogsearch.service.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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
        private String datetime;
    }
}
