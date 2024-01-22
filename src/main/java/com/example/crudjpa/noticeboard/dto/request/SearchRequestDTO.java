package com.example.crudjpa.noticeboard.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchRequestDTO {
    @NonNull
    private Integer page;
    @NonNull
    private Integer size;
    @NonNull
    private String sort;
    private String searchType;
    private String searchKeyword;
}
