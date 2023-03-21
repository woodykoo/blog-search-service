package com.woody.webservice.blogsearch.enums;

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
