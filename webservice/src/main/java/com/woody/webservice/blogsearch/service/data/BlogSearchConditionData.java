package com.woody.webservice.blogsearch.service.data;

import com.woody.webservice.blogsearch.enums.Sort;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
