package com.woody.client.kakao.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by woody 2023.03.22
 * 카카오 블로그 검색 응답 DTO
 * */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoBlogSearchResponse {

    private Meta meta;
    private List<Document> documents;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Meta {

        @JsonProperty("total_count")
        private int totalCount;

        @JsonProperty("pageable_count")
        private int pageableCount;

        @JsonProperty("is_end")
        private boolean isEnd;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Document {
        private String title;
        private String contents;
        private String url;
        private String blogname;
        private String thumbnail;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        private LocalDateTime datetime;
    }
}
