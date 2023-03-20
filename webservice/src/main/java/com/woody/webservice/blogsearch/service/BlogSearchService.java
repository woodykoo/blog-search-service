package com.woody.webservice.blogsearch.service;

import com.woody.webservice.blogsearch.service.data.BlogSearchConditionData;
import com.woody.webservice.blogsearch.service.data.BlogSearchResultData;


/**
 * Created by woody 2023.03.20
 * 블로그 검색 서비스
 * */
public interface BlogSearchService {

    /**
     * 블로그 검색 서비스
     * @param searchCondition 검색조건
     * @return BlogSearchResultData
     * */
    BlogSearchResultData searchBlog(BlogSearchConditionData searchCondition);
}
