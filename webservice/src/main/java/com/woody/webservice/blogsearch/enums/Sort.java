package com.woody.webservice.blogsearch.enums;

/**
 * Created by woody 2023.03.20
 * 검색 정렬 조건 enum
 * */
public enum Sort {
    ACCURACY("accuracy", "정확도 순으로 검색"),
    RECENCY("recency", "최신 순으로 검색");

    private final String value;

    private final String desc;

    Sort(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    public String getValue() {
        return value;
    }

    public static Sort fromValue(String value) {
        for (Sort sort : values()) {
            if (sort.getValue().equals(value)) {
                return sort;
            }
        }
        return ACCURACY;
    }
}
