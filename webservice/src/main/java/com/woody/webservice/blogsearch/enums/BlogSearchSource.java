package com.woody.webservice.blogsearch.enums;

/**
 * Created by woody 2023.03.22
 * 블로그 조회 서비스 enum
 * */
public enum BlogSearchSource {
    KAKAO("kakaoBlogSearchServiceImpl"),
    NAVER("naverBlogSearchServiceImpl");

    BlogSearchSource(String serviceName) {
        this.serviceName = serviceName;
    }

    private final String serviceName;

    public String getServiceName() {
        return this.serviceName;
    }
}
