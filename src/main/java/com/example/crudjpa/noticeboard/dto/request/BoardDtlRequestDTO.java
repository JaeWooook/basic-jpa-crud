package com.example.crudjpa.noticeboard.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class BoardDtlRequestDTO {
    //C R U
    @NonNull
    private String boardFlag;
    private long boardId;
}
