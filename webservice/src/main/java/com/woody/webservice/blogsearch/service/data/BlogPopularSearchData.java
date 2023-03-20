package com.woody.webservice.blogsearch.service.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlogPopularSearchData {
    private int rank;
    private String keyword;
    private int count;
}
