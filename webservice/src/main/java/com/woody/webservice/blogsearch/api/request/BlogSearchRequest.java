package com.woody.webservice.blogsearch.api.request;

import com.woody.webservice.blogsearch.enums.Sort;
import com.woody.webservice.blogsearch.service.data.BlogSearchConditionData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

/**
 * Created by woody 2023.03.20
 * 블로그 검색 요청 DTO
 * */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogSearchRequest {

    @NotBlank(message = "검색어는 필수값 입니다.")
    private String keyword;

    @Max(value = 50, message = "최대 50페이지 까지 검색이 가능 합니다.")
    private int page = 1;

    @Max(value = 50, message = "최대 50개씩 검색이 가능 합니다.")
    private int size = 10;

    private String sort;

    public BlogSearchConditionData toSearchCondition() {
        return new BlogSearchConditionData(keyword, page, size, Sort.fromValue(sort));
    }
}
