package com.woody.webservice.blogsearch.service.data;

import com.woody.webservice.blogsearch.enums.Sort;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by woody 2023.03.22
 * 블로그 검색 조회 조건 DTO
 * */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BlogSearchConditionData {
    private String keyword;

    private int page;

    private int size;

    private Sort sort;
}
