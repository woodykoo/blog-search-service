package com.woody.webservice.blogsearch.service.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by woody 2023.03.22
 * 인기 검색어 DTO
 * */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlogPopularSearchData {
    private int rank;
    private String keyword;
    private int count;
}
