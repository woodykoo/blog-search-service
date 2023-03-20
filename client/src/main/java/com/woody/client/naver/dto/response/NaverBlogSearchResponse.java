package com.woody.client.naver.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NaverBlogSearchResponse {

    private int total;

    private int start;

    private int display;

    private List<Item> items;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        private String title;
        private String link;
        private String description;
        private String bloggername;
        private String bloggerlink;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
        private LocalDate postdate;
    }
}
