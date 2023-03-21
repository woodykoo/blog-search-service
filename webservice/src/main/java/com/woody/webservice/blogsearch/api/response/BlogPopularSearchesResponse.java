package com.woody.webservice.blogsearch.api.response;

import com.woody.webservice.blogsearch.service.data.BlogPopularSearchData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by woody 2023.03.20
 * 인기 검색어 조회 응답 DTO
 * */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlogPopularSearchesResponse {

    private List<BlogPopularSearch> rankList;

    public static BlogPopularSearchesResponse from(List<BlogPopularSearchData> blogPopularSearchDataList) {
        return new BlogPopularSearchesResponse(blogPopularSearchDataList.stream().map(BlogPopularSearch::new).collect(Collectors.toList()));
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BlogPopularSearch {
        private int rank;
        private String keyword;
        private int count;

        public BlogPopularSearch(BlogPopularSearchData blogPopularSearchData) {
            this.rank = blogPopularSearchData.getRank();
            this.keyword = blogPopularSearchData.getKeyword();
            this.count = blogPopularSearchData.getCount();
        }
    }
}
